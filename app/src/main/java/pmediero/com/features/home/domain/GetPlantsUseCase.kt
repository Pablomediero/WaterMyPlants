package pmediero.com.features.home.domain

import kotlinx.coroutines.flow.Flow
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.realm.PlantEntity

class GetPlantsUseCase (
private val plantRepository: PlantRepository
){
    suspend operator fun invoke(): Flow<List<PlantEntity>> {
        return plantRepository.getPlants()
    }
}

