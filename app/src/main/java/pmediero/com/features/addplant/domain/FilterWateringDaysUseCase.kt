package pmediero.com.features.addplant.domain

class FilterWateringDaysUseCase {

    fun execute(wateringDays: Map<String, Boolean>): String {
        return if (wateringDays.all { it.value }) {
            "EveryDay"
        } else {
            wateringDays.filter { it.value }.keys
                .joinToString(" ") { it.take(2) }
        }
    }
}