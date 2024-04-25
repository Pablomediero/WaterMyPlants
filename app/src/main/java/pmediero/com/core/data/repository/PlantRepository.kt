package pmediero.com.core.data.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import pmediero.com.core.data.mappers.toPlant
import pmediero.com.core.data.mappers.toPlantEntity
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.core.model.util.LocalError
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError

class PlantRepository(
    private val realm: Realm
) {


    suspend fun savePlant(plant: Plant): Result<Plant, RootError> {
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

    suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError> {
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

    suspend fun observePlants(): Flow<List<Plant>> = realm
        .query<PlantEntity>()
        .asFlow()
        .map { results ->
            results.list.toList().map {
                toPlant(it)
            }
        }


    suspend fun getPlants(): List<Plant> = realm.query<PlantEntity>().find().map { toPlant(it) }
    suspend fun getPlantById(plantIdParam: String):Plant {
        val plantEntity = ObjectId(plantIdParam)
        return toPlant(realm.query<PlantEntity>("_id == $0", plantEntity).find().first())
    }


}

