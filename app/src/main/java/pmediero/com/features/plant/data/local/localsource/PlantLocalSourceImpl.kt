package pmediero.com.features.plant.data.local.localsource

import android.os.Build
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
): PlantLocalSource{
     override suspend fun savePlant(plant: Plant): Result<Plant, RootError> {
        return try {
            val plantEntity = toPlantEntity(plant)
            realm.write {
                copyToRealm(plantEntity, UpdatePolicy.ALL)
            }

            Result.Success(toPlant(plantEntity))
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

     override suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError> {
        return try {
            realm.write {
                plants.forEach { plant ->
                    copyToRealm(toPlantEntity(plant), UpdatePolicy.ALL)
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
            results.list.toList().map {
                toPlant(it)
            }
        }


     override suspend fun getPlants(): List<Plant> = realm.query<PlantEntity>().find().map { toPlant(it) }
     override suspend fun getPlantById(plantIdParam: String): Plant {
        val plantEntity = BsonObjectId(plantIdParam)
        return toPlant(realm.query<PlantEntity>("_id == $0", plantEntity).find().first())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getFilterUpcomingPlants(): List<Plant> {
        val listPlants = realm.query<PlantEntity>().find().map { toPlant(it) }
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