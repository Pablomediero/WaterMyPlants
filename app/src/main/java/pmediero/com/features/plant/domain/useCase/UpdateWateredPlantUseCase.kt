package pmediero.com.features.plant.domain.useCase

import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError

class UpdateWateredPlantUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant): Result<Unit, RootError> {
        return plantRepository.savePlant(plant = plant)
    }
}