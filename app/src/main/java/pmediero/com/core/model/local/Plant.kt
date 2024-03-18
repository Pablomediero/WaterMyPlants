package pmediero.com.core.model.local

data class Plant(
    var id: Long = 0,
    var name: String = "",
    var wateringDays: String = "",
    var wateringTime: String = "",
    var waterAmount: String = "",
    var plantSize: String = "",
    var description: String = "",
    var photo:String = ""
)