package pmediero.com.features.addplant.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pmediero.com.features.addplant.domain.AddPlantUseCase
import pmediero.com.features.addplant.domain.FilterWateringDaysUseCase
import pmediero.com.features.addplant.presentation.root.AddPlantAction
import pmediero.com.features.addplant.presentation.root.AddPlantState
import pmediero.com.features.addplant.presentation.root.AddPlantUiEvent

class AddPlantViewModel(
    private val filterWateringDaysUseCase: FilterWateringDaysUseCase,
    private val addPlantUseCase: AddPlantUseCase,
) : ViewModel() {

    var state by mutableStateOf(AddPlantState())
        private set
    private val _uiEvent = Channel<AddPlantUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: AddPlantAction) {
        when (action) {
            is AddPlantAction.OnCreatePlantClick -> {
                viewModelScope.launch {
                    updateLoadingState(true)
                    addPlantUseCase(action.plant).fold(
                        onError = {

                        },
                        onSuccess = {
                            _uiEvent.send(AddPlantUiEvent.NavigateToHome)
                        }
                    )
                    updateLoadingState(false)
                }
            }
            is AddPlantAction.OnAddImageButtonClick -> {
                state = state.copy(
                    plantPhoto = action.plantPhoto,
                    isPhotoSelected = true
                )

            }
            is AddPlantAction.OnPlantNameChange -> {
                state = state.copy(
                    plantName = action.plantName
                )
            }
            is AddPlantAction.OnPlantSizeChange -> {
                state = state.copy(
                    plantSize = action.plantSize
                )
            }

            is AddPlantAction.OnPlantWaterAmountChange -> {
                state = state.copy(
                    waterAmount = action.waterAmount
                )
            }

            is AddPlantAction.OnPlantWateringDaysChange -> {
                state = state.copy(
                    wateringDays = filterWateringDaysUseCase(action.wateringDays)
                )
            }

            is AddPlantAction.OnPlantWateringTimeChange -> {
                state = state.copy(
                    wateringTime = action.wateringTime
                )
            }

            is AddPlantAction.OnPlantDescriptionChange -> {
                state = state.copy(
                    plantDescription = action.plantDescription
                )
            }
        }

    }

    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }
}