package pmediero.com.core.data.repository

import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pmediero.com.WaterMyPlantApp
import pmediero.com.core.data.mappers.toPlant
import pmediero.com.core.data.mappers.toPlantEntity
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

class PlantRepository {
    private val realm = WaterMyPlantApp.realm

    suspend fun addPlant(plant: Plant){
        realm.write {
            copyToRealm(toPlantEntity(plant), UpdatePolicy.ALL)
        }
    }

    fun getPlants(): Flow<List<Plant>> {
        return realm
            .query<PlantEntity>()
            .asFlow()
            .map { results ->
                results.list.toList().map { toPlant(it) }
            }
    }

}