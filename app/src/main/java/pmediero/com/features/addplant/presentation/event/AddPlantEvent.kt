package pmediero.com.features.addplant.presentation.event

sealed class AddPlantEvent
data object OnCreatePlant : AddPlantEvent()
data class OnPlantNameChange(val plantName: String) : AddPlantEvent()
data class OnPlantWateringDaysChange(val wateringDays: Map<String, Boolean>) : AddPlantEvent()
data class OnPlantWateringTimeChange(val wateringTime: String) : AddPlantEvent()
data class OnPlantWaterAmountChange(val waterAmount: String) : AddPlantEvent()
data class OnPlantSizeChange(val plantSize: String) : AddPlantEvent()
data class OnPlantDescriptionChange(val plantDescription: String) : AddPlantEvent()
