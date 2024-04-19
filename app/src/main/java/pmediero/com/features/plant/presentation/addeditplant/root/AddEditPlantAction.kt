package pmediero.com.features.plant.presentation.addeditplant.root

import pmediero.com.core.model.local.Plant

sealed class AddEditPlantAction{
    data class OnCreateEditPlantClick(val plant: Plant) : AddEditPlantAction()
    data class OnEditPlantNameChange(val plantName: String) : AddEditPlantAction()
    data class OnEditPlantWateringDaysChange(val wateringDays: Map<String, Boolean>) : AddEditPlantAction()
    data class OnEditPlantWateringTimeChange(val wateringTime: String) : AddEditPlantAction()
    data class OnEditPlantWaterAmountChange(val waterAmount: String) : AddEditPlantAction()
    data class OnEditPlantSizeChange(val plantSize: String) : AddEditPlantAction()
    data class OnEditPlantDescriptionChange(val plantDescription: String) : AddEditPlantAction()
    data class OnAddImageButtonClickEdit(val plantPhoto: String) : AddEditPlantAction()
    data object OnRemoveImageButtonClick: AddEditPlantAction()


}
