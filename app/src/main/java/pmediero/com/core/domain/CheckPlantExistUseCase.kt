package pmediero.com.core.domain

import pmediero.com.features.plant.domain.repository.PlantRepository

class CheckPlantExistUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(): Boolean = plantRepository.getPlants().isEmpty()
}

