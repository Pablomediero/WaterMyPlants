package pmediero.com.features.plant.presentation.home.root

import pmediero.com.core.model.local.Plant
import pmediero.com.features.plant.presentation.home.model.TabType

data class HomeState(

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
    var notificationAux: Int = 0,
    var tabSelected: TabType = TabType.UPCOMING,
    var plantListMap: Map<TabType, List<Plant>> = mapOf(),
    var isPlantWatered: Boolean = false
)