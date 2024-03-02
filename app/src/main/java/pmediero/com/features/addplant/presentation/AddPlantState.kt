package pmediero.com.features.addplant.presentation

data class AddPlantState(
    var plantName: String = "",
    //var wateringDays:Map<String, Boolean> = emptyMap(),
    var wateringDays: String = "",
    var wateringTime: String = "",
    var waterAmount: String = "",
    var plantSize: String = "",
    var plantDescription: String = ""

)
