package pmediero.com.core.data.repository

import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pmediero.com.WaterMyPlantApp
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.realm.PlantEntity

class PlantRepository {
    private val realm = WaterMyPlantApp.realm

    suspend fun addPlant(plant: Plant){
        realm.write {
            val plantExample = PlantEntity().apply {
                name = plant.name
                wateringDays = plant.wateringDays
                wateringTime = plant.wateringTime
                waterAmount = plant.waterAmount
                plantSize = plant.plantSize
                description = plant.description
            }
            copyToRealm(plantExample, UpdatePolicy.ALL)
        }
    }

    fun getPlants(): Flow<List<PlantEntity>> {
        return realm
            .query<PlantEntity>()
            .asFlow()
            .map { results ->
                results.list.toList()
            }
    }

}