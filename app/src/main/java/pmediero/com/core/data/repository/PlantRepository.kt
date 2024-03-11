package pmediero.com.core.data.repository

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


    suspend fun addPlant(plant: Plant): Result<Unit, RootError>{

        return try {
            realm.write {
                copyToRealm(toPlantEntity(plant), UpdatePolicy.ALL)
            }
            Result.Success(Unit)
        }catch (e: Exception){
            Result.Error(LocalError)
        }

    }

    fun getPlants(): Result<Flow<List<Plant>>, RootError> {
        return try {
            val plants = realm
                .query<PlantEntity>()
                .asFlow()
                .map { results ->
                    results.list.toList().map { toPlant(it) }
                }
            Result.Success(plants)
        }catch (e: Exception){
            Result.Error(LocalError)
        }

    }

}