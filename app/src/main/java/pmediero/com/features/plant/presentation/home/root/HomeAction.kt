package pmediero.com.features.plant.presentation.home.root

import pmediero.com.core.model.local.Plant

sealed class HomeAction {
    data class OnCardLongClick(val plant: Plant): HomeAction()
    data class OnIconCardPlantClicked(val plant: Plant): HomeAction()
    data object NavigateAddPlant: HomeAction()
    data object OnClickPlant: HomeAction()
    data class OnTabClicked(val index: Int): HomeAction()
    data class OnDeletePlant(val plant: Plant): HomeAction()
}
