package pmediero.com.features.addplant.presentation.root

import pmediero.com.core.model.local.Plant

sealed class AddPlantAction{
    data class OnCreatePlantClick(val plant: Plant) : AddPlantAction()
    data class OnPlantNameChange(val plantName: String) : AddPlantAction()
    data class OnPlantWateringDaysChange(val wateringDays: Map<String, Boolean>) : AddPlantAction()
    data class OnPlantWateringTimeChange(val wateringTime: String) : AddPlantAction()
    data class OnPlantWaterAmountChange(val waterAmount: String) : AddPlantAction()
    data class OnPlantSizeChange(val plantSize: String) : AddPlantAction()
    data class OnPlantDescriptionChange(val plantDescription: String) : AddPlantAction()
    data class OnPlantPhotoChange(val plantPhoto: String) : AddPlantAction()
    data class OnIconButtonChange(val iconButtonName: String) : AddPlantAction()
}
