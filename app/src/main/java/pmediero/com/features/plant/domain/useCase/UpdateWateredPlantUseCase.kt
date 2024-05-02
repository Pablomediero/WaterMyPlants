package pmediero.com.features.plant.domain.useCase

import pmediero.com.core.model.local.Plant
import pmediero.com.core.model.util.Result
import pmediero.com.core.model.util.RootError
import pmediero.com.features.plant.data.repository.PlantRepository

class UpdateWateredPlantUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant): Result<Plant, RootError> {
        return plantRepository.savePlant(plant = plant)
    }
}