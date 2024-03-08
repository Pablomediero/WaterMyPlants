package pmediero.com.features.addplant.domain

import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError

class AddPlantUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant): Result<Unit, RootError> {
        return plantRepository.addPlant(plant = plant)
    }
}