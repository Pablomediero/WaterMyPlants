package pmediero.com.features.home.presentation.root

import pmediero.com.core.model.local.Plant

sealed class HomeAction {
    data class OnCardLongClick(val plant: Plant): HomeAction()
    data class OnDeletePlant(val plant: Plant): HomeAction()
}
