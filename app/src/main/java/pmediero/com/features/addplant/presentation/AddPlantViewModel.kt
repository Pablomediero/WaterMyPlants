package pmediero.com.features.addplant.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pmediero.com.features.addplant.domain.FilterWateringDaysUseCase
import pmediero.com.features.addplant.presentation.action.AddPlantAction
import pmediero.com.features.addplant.presentation.action.OnCreatePlant
import pmediero.com.features.addplant.presentation.action.OnPlantDescriptionChange
import pmediero.com.features.addplant.presentation.action.OnPlantNameChange
import pmediero.com.features.addplant.presentation.action.OnPlantSizeChange
import pmediero.com.features.addplant.presentation.action.OnPlantWaterAmountChange
import pmediero.com.features.addplant.presentation.action.OnPlantWateringDaysChange
import pmediero.com.features.addplant.presentation.action.OnPlantWateringTimeChange

class AddPlantViewModel() : ViewModel() {
    private val filterWateringDaysUseCase = FilterWateringDaysUseCase()

    var state by mutableStateOf(AddPlantState())
        private set

    fun onEvent(event: AddPlantAction) {
        when (event) {
            OnCreatePlant -> {
                Log.d("CreatePlant",state.toString())
            }
            is OnPlantNameChange -> {
                state = state.copy(
                    plantName = event.plantName
                )
            }

            is OnPlantSizeChange -> {
                state = state.copy(
                    plantSize = event.plantSize
                )
            }

            is OnPlantWaterAmountChange -> {
                state = state.copy(
                    waterAmount = event.waterAmount
                )
            }

            is OnPlantWateringDaysChange -> {
//                state = state.copy(
//                    wateringDays = when {
//                        event.wateringDays.all { it.value } -> "EveryDay"
//                        else -> event.wateringDays.filter { it.value }.keys.joinToString {
//                            if (it.length >= 2) it.substring(0, 2) else it
//                        }
//                    }
//                )
                state = state.copy(
                    wateringDays = filterWateringDaysUseCase.execute(event.wateringDays)
                )
            }

            is OnPlantWateringTimeChange -> {
                state = state.copy(
                    wateringTime = event.wateringTime
                )
            }

            is OnPlantDescriptionChange -> {
                state = state.copy(
                    plantDescription = event.plantDescription
                )
            }

        }
    }
}