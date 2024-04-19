package pmediero.com.features.plant.domain.useCase

import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant

class GetPlantByIdUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plantIdParam: String): Plant {
        return plantRepository.getPlantById(plantIdParam = plantIdParam)
    }
}