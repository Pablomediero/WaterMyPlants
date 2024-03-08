package pmediero.com.features.home.domain

import kotlinx.coroutines.flow.Flow
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError

class GetPlantsUseCase (
private val plantRepository: PlantRepository
){
    operator fun invoke(): Result<Flow<List<Plant>>, RootError> {
        return plantRepository.getPlants()
    }
}

