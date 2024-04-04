package pmediero.com.core.data.repository

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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


    suspend fun addPlant(plant: Plant): Result<Unit, RootError> {

        return try {
            val map = toPlantEntity(plant)
            realm.write {
                copyToRealm(map, UpdatePolicy.ALL)
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LocalError)
        }

    }

    suspend fun updateIsWateredPlant(plant: Plant): Result<Unit, RootError> {
        return try {
//            val cachePlant = toPlantEntity(plant)
//            Log.i("BBDDRESULT", "UPDATE PLANTS ${cachePlant._id}")
//            realm.write {
//                val updatePlant = query<PlantEntity>("_id == $cachePlant._id").find().first()
//                updatePlant.isWatered = cachePlant.isWatered
//
//            }
            realm.write {
                copyToRealm(toPlantEntity(plant), UpdatePolicy.ALL)
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
                Log.i("BBDDRESULT", "GET PLANTS ${it._id}")
                toPlant(it)


            }
        }

    suspend fun getPlants(): List<Plant> = realm.query<PlantEntity>().find().map { toPlant(it) }

}

