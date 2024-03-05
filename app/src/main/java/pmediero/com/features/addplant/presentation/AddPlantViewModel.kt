package pmediero.com.features.addplant.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pmediero.com.core.util.UiText
import pmediero.com.features.addplant.domain.FilterWateringDaysUseCase
import pmediero.com.features.addplant.presentation.action.AddPlantAction
import pmediero.com.features.addplant.presentation.action.OnCreatePlant
import pmediero.com.features.addplant.presentation.action.OnPlantDescriptionChange
import pmediero.com.features.addplant.presentation.action.OnPlantNameChange
import pmediero.com.features.addplant.presentation.action.OnPlantSizeChange
import pmediero.com.features.addplant.presentation.action.OnPlantWaterAmountChange
import pmediero.com.features.addplant.presentation.action.OnPlantWateringDaysChange
import pmediero.com.features.addplant.presentation.action.OnPlantWateringTimeChange

class AddPlantViewModel(
    private val filterWateringDaysUseCase : FilterWateringDaysUseCase
) : ViewModel() {

    var state by mutableStateOf(AddPlantState())
        private set

    fun onEvent(event: AddPlantAction) {
        when (event) {
            OnCreatePlant -> {
                Log.d("CreatePlant",state.toString())
            }
            is OnPlantNameChange -> {
                state = state.copy(
                    plantName = UiText.DynamicString(event.plantName)
                    //plantName = event.plantName
                )
            }

            is OnPlantSizeChange -> {
                state = state.copy(
                    plantSize =UiText.DynamicString(event.plantSize)
                )
            }

            is OnPlantWaterAmountChange -> {
                state = state.copy(
                    waterAmount = UiText.DynamicString(event.waterAmount)
                )
            }

            is OnPlantWateringDaysChange -> {
                state = state.copy(
                    wateringDays = UiText.DynamicString(filterWateringDaysUseCase(event.wateringDays))
                )
            }

            is OnPlantWateringTimeChange -> {
                state = state.copy(
                    wateringTime = UiText.DynamicString(event.wateringTime)
                )
            }

            is OnPlantDescriptionChange -> {
                state = state.copy(
                    plantDescription =UiText.DynamicString(event.plantDescription)
                )
            }
        }
    }
}