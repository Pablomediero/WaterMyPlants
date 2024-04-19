package pmediero.com.features.plant.presentation.detailplant.root

import pmediero.com.core.model.local.Plant

sealed class DetailAction {
    data object OnReturnClick: DetailAction()
    data class OnEditButtonClick(val plantIdParam: String): DetailAction()
    data class OnIsWaterUpdateButtonClick(val plant: Plant): DetailAction()
}