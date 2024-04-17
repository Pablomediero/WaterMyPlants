package pmediero.com.features.plant.presentation.detailplant.root

import pmediero.com.core.model.local.Plant

data class DetailState (
    var isLoading: Boolean = false,
    var plant: Plant = Plant(
        id = "",
        name = "",
        plantSize = "",
        waterAmount = "",
        wateringTime = "",
        wateringDays = "",
        description = ""
    ),
)