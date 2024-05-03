package pmediero.com.features.plant.domain.useCase

import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.data.repository.PlantRepository

class GetPlantByIdUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plantIdParam: String): Plant {
        return plantRepository.getPlantById(plantIdParam = plantIdParam)
    }
}