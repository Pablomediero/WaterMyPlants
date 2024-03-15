package pmediero.com.features.home.presentation.root

import pmediero.com.core.model.local.Plant

data class HomeState(

    var isLoading: Boolean = false,
    var plantList: List<Plant> = emptyList()
)