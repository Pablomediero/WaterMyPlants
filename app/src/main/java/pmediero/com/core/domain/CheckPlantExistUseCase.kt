package pmediero.com.core.domain

import kotlinx.coroutines.flow.first
import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError
import pmediero.com.core.model.util.map

class CheckPlantExistUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(): Result<Boolean, RootError> =
        plantRepository.getPlants().map {
            it.first().isEmpty()
        }

}

