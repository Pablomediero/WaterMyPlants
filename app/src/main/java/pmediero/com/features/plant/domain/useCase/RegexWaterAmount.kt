package pmediero.com.features.plant.domain.useCase

class RegexWaterAmount {
    operator fun invoke(regexExpression: Regex, waterAmount: String, maxChar: Int ): String?{
        return if (waterAmount.length <= maxChar &&
            (waterAmount.matches(regexExpression) || waterAmount.isEmpty())
        ) {
            waterAmount
        } else {
            null
        }
    }
}