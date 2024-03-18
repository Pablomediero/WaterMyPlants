package pmediero.com.features.addplant.presentation.root

data class AddPlantState(
    var plantName: String = "",
    var wateringDays: String = "",
    var wateringTime: String = "",
    var waterAmount: String = "",
    var plantSize: String = "",
    var plantDescription: String = "",
    var plantPhoto: String = "",
    var isLoading: Boolean = false,
    var isPhotoSelected: Boolean = false
)
