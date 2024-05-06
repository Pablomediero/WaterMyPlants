package pmediero.com.features.plant.presentation.detailplant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pmediero.com.features.plant.domain.repository.PlantRepository
import pmediero.com.features.plant.domain.useCase.GetPlantByIdUseCase
import pmediero.com.features.plant.presentation.detailplant.root.DetailAction
import pmediero.com.features.plant.presentation.detailplant.root.DetailState

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    private val plantRepository: PlantRepository

) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    private val plantIdParam = savedStateHandle.get<String>("plantIdParam")

    init {
        viewModelScope.launch {
            updateLoadingState(true)
            if (plantIdParam != null) {
                val plant = getPlantByIdUseCase(plantIdParam)
                state = state.copy(
                    plant = plant
                )
            }
            updateLoadingState(false)
        }
    }
    fun onAction(action: DetailAction){
        when (action){
            is DetailAction.OnIsWaterUpdateButtonClick -> {
                action.plant.isWatered = !action.plant.isWatered
                viewModelScope.launch {
                    updateLoadingState(true)
                    plantRepository.savePlant(action.plant).fold(
                        onError = {

                        },
                        onSuccess = {

                        }
                    )
                    updateLoadingState(false)
                }
            }

            else -> {}
        }
    }

    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }
}