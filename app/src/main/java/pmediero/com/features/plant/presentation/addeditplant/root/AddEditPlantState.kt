package pmediero.com.features.plant.presentation.addeditplant.root

data class AddEditPlantState(
    var plantId: String = "",
    var plantName: String = "",
    var wateringDays: String = "",
    var wateringTime: String = "",
    var waterAmount: String = "",
    var plantSize: String = "",
    var plantDescription: String = "",
    var plantPhoto: String = "",
    var isLoading: Boolean = false,
    var isEditPlant: Boolean = false,
    var isPhotoSelected: Boolean = false
)
