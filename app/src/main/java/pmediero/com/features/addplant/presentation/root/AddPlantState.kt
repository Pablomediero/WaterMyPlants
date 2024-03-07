package pmediero.com.features.addplant.presentation.root

import pmediero.com.R
import pmediero.com.core.model.util.UiText

data class AddPlantState(
    var plantName: UiText = UiText.StringResource((R.string.plant_name_state)),
    var wateringDays: UiText =  UiText.StringResource((R.string.watering_days_state)),
    var wateringTime: UiText = UiText.StringResource((R.string.watering_time_state)),
    var waterAmount: UiText = UiText.StringResource((R.string.water_amount_state)),
    var plantSize: UiText = UiText.StringResource((R.string.plant_size_state)),
    var plantDescription: UiText = UiText.StringResource((R.string.plant_description_state))

)
