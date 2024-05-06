package pmediero.com.features.plant.domain.repository

import kotlinx.coroutines.flow.Flow
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError

interface PlantRepository {
    suspend fun savePlant(plant: Plant):Result<Plant, RootError>
    suspend fun saveAllPlant(plants: List<Plant>): Result<Unit, RootError>
    suspend fun observePlants(): Flow<List<Plant>>
    suspend fun getPlants(): List<Plant>
    suspend fun getPlantById(plantIdParam: String): Plant
    suspend fun getFilterUpcomingPlants(): List<Plant>
}
