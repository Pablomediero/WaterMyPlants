package pmediero.com.features.addplant.domain

import pmediero.com.core.data.repository.PlantRepository
import pmediero.com.core.model.local.Plant

class AddPlantUseCase(
    private val plantRepository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant){
        return plantRepository.addPlant(plant = plant)
    }
}