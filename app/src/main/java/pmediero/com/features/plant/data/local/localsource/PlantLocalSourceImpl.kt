package pmediero.com.features.plant.data.local.localsource

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.core.model.util.LocalError
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError
import pmediero.com.features.plant.data.local.mappers.toPlant
import pmediero.com.features.plant.data.local.mappers.toPlantEntity
import java.time.LocalDate
import java.util.Locale

class PlantLocalSourceImpl(
    private val realm: Realm,
) : PlantLocalSource {
    override suspend fun savePlant(plant: Plant): Result<Plant, RootError> {
        return try {
            val plantEntity = plant.toPlantEntity(plant)
            realm.write {
                copyToRealm(plantEntity, UpdatePolicy.ALL)
            }

            Result.Success(plantEntity.toPlant(plantEntity))
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

    override suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError> {
        return try {
            Log.d("WorkerPlantsUpdate", "Entra SourceImpl")
            realm.write {
                plants.forEach { plant ->
                    Log.d("WorkerPlantsUpdate", "${plant.name} is ${plant.isWatered}")
                    copyToRealm(plant.toPlantEntity(plant), UpdatePolicy.ALL)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

    override suspend fun observePlants(): Flow<List<Plant>> = realm
        .query<PlantEntity>()
        .asFlow()
        .map { results ->
            results.list.toList().map {plant -> plant.toPlant(plant)
            }
        }
    override suspend fun getPlants(): List<Plant> =
        realm.query<PlantEntity>().find().map { plant -> plant.toPlant(plant) }

    override suspend fun getPlantById(plantIdParam: String): Plant {
        val plantEntityId = BsonObjectId(plantIdParam)
        val plantEntity = realm.query<PlantEntity>("_id == $0", plantEntityId).find().first()
        return plantEntity.toPlant(plantEntity)
    }

    override suspend fun deletePlantById(plantIdParam: String): Result<Unit, RootError> {
        return try {
            val plantEntityId = BsonObjectId(plantIdParam)
            realm.write {
                val deletePlant = query<PlantEntity>("_id == $0", plantEntityId).find().first()
                delete(deletePlant)
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LocalError)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getFilterUpcomingPlants(): List<Plant> {
        val listPlants = realm.query<PlantEntity>().find()
            .map { plantEntity -> plantEntity.toPlant(plantEntity) }
        val currentDayOfWeek = LocalDate.now().dayOfWeek.name.substring(0, 2).lowercase(Locale.ROOT)
        return listPlants.filter { plant ->
            plant.wateringDays.split(" ").any { day ->
                day.equals(
                    "everyday",
                    ignoreCase = true
                ) || day.lowercase(Locale.ROOT) == currentDayOfWeek
            }
        }
    }
}