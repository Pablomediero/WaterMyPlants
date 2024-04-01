package pmediero.com.features.home.presentation.root

import pmediero.com.core.model.local.Plant
import pmediero.com.features.home.presentation.model.TabType

data class HomeState(

    var isLoading: Boolean = false,
    var plant: Plant = Plant(
        id = 1,
        name = "",
        plantSize = "",
        waterAmount = "",
        wateringTime = "",
        wateringDays = "",
        description = ""
    ),
    var tabSelected: TabType = TabType.UPCOMING,
    var plantList: List<Plant> = emptyList()
)