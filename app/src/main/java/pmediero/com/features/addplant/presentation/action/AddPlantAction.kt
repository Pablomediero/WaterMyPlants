package pmediero.com.features.addplant.presentation.action

sealed class AddPlantAction
data object OnCreatePlant : AddPlantAction()
data class OnPlantNameChange(val plantName: String) : AddPlantAction()
data class OnPlantWateringDaysChange(val wateringDays: Map<String, Boolean>) : AddPlantAction()
data class OnPlantWateringTimeChange(val wateringTime: String) : AddPlantAction()
data class OnPlantWaterAmountChange(val waterAmount: String) : AddPlantAction()
data class OnPlantSizeChange(val plantSize: String) : AddPlantAction()
data class OnPlantDescriptionChange(val plantDescription: String) : AddPlantAction()
