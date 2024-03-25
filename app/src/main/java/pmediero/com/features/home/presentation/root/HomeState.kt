package pmediero.com.features.home.presentation.root

import pmediero.com.core.model.local.Plant

data class HomeState(

    var isLoading: Boolean = false,
    var plant: Plant =  Plant(
        id = 1,
        name = "",
        plantSize = "",
        waterAmount = "",
        wateringTime = "",
        wateringDays = "",
        description = ""
    ),
    var plantList: List<Plant> = emptyList()
)