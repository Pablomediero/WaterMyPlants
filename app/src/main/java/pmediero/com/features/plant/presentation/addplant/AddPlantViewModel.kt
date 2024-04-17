package pmediero.com.features.plant.presentation.addplant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pmediero.com.features.plant.domain.useCase.AddPlantUseCase
import pmediero.com.features.plant.domain.useCase.FilterWateringDaysUseCase
import pmediero.com.features.plant.domain.useCase.GetPlantByIdUseCase
import pmediero.com.features.plant.presentation.addplant.root.AddPlantAction
import pmediero.com.features.plant.presentation.addplant.root.AddPlantState
import pmediero.com.features.plant.presentation.addplant.root.AddPlantUiEvent

class AddPlantViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val filterWateringDaysUseCase: FilterWateringDaysUseCase,
    private val addPlantUseCase: AddPlantUseCase,
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
) : ViewModel() {

    var state by mutableStateOf(AddPlantState())
        private set
    private val _uiEvent = Channel<AddPlantUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _plantIdParam = savedStateHandle.get<String>("plantIdParam").takeIf { it != null }


    init {
        if (!_plantIdParam.isNullOrEmpty() && _plantIdParam != "0") {
            viewModelScope.launch {
                updateLoadingState(true)
                val plant =  getPlantByIdUseCase(_plantIdParam)
                state = state.copy(
                    plantName = plant.name,
                    wateringDays = plant.wateringDays,
                    wateringTime = plant.wateringTime,
                    waterAmount = plant.waterAmount,
                    plantSize = plant.plantSize,
                    plantDescription = plant.description,
                    plantPhoto = plant.photo,
                    isPhotoSelected = true
                )
                updateLoadingState(false)
            }
        }
    }

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

            is AddPlantAction.OnRemoveImageButtonClick -> {
                state = state.copy(
                    plantPhoto = "",
                    isPhotoSelected = false
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