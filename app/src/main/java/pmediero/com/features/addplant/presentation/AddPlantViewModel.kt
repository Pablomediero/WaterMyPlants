package pmediero.com.features.addplant.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pmediero.com.core.model.util.UiText
import pmediero.com.features.addplant.domain.AddPlantUseCase
import pmediero.com.features.addplant.domain.FilterWateringDaysUseCase
import pmediero.com.features.addplant.presentation.root.AddPlantAction
import pmediero.com.features.addplant.presentation.root.AddPlantState
import pmediero.com.features.addplant.presentation.root.OnCreatePlantClick
import pmediero.com.features.addplant.presentation.root.OnPlantDescriptionChange
import pmediero.com.features.addplant.presentation.root.OnPlantNameChange
import pmediero.com.features.addplant.presentation.root.OnPlantSizeChange
import pmediero.com.features.addplant.presentation.root.OnPlantWaterAmountChange
import pmediero.com.features.addplant.presentation.root.OnPlantWateringDaysChange
import pmediero.com.features.addplant.presentation.root.OnPlantWateringTimeChange

class AddPlantViewModel(
    private val filterWateringDaysUseCase: FilterWateringDaysUseCase,
    private val addPlantUseCase: AddPlantUseCase,
) : ViewModel() {

    var state by mutableStateOf(AddPlantState())
        private set

     fun onEvent(event: AddPlantAction) {
        when (event) {
            is OnCreatePlantClick -> {
                viewModelScope.launch {
                    addPlantUseCase(event.plant)
                }
            }

            is OnPlantNameChange -> {
                state = state.copy(
                    plantName = UiText.DynamicString(event.plantName)
                )
            }

            is OnPlantSizeChange -> {
                state = state.copy(
                    plantSize = UiText.DynamicString(event.plantSize)
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
                    plantDescription = UiText.DynamicString(event.plantDescription)
                )
            }

            else -> {}
        }
    }
}