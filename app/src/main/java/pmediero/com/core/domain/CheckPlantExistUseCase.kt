package pmediero.com.core.domain

import pmediero.com.core.data.repository.PlantRepository

class CheckPlantExistUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(): Boolean = plantRepository.getPlants().isEmpty()
}

