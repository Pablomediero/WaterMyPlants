package pmediero.com.core.model.local

data class Plant(
    var id: String = "0",
    var name: String = "",
    var wateringDays: String = "",
    var lastWateredDate: String = "",
    var wateringTime: String = "",
    var waterAmount: String = "",
    var plantSize: String = "",
    var description: String = "",
    var photo:String = "",
    var isWatered: Boolean = false
)