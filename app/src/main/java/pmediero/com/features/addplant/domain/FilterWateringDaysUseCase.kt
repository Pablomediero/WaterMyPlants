package pmediero.com.features.addplant.domain

class FilterWateringDaysUseCase {
    operator fun invoke(wateringDays: Map<String, Boolean>): String {
        return if (wateringDays.any { it.value }) {
            if (wateringDays.all { it.value }) {
                "Everyday"
            } else {
                wateringDays.filter { it.value }.keys
                    .sortedBy { day ->
                        when (day) {
                            "Monday" -> 0
                            "Tuesday" -> 1
                            "Wednesday" -> 2
                            "Thursday" -> 3
                            "Friday" -> 4
                            "Saturday" -> 5
                            "Sunday" -> 6
                            else -> -1
                        }
                    }
                    .joinToString(" ") { it.take(2) }
            }
        } else {
            ""
        }
    }
}