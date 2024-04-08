package pmediero.com.features.plant.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pmediero.com.features.plant.domain.useCase.GetFilteredPlantsUseCase
import pmediero.com.features.plant.domain.useCase.UpdateWateredPlantUseCase
import pmediero.com.features.plant.presentation.home.model.TabType
import pmediero.com.features.plant.presentation.home.root.HomeAction
import pmediero.com.features.plant.presentation.home.root.HomeState

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel(
    private val getFilteredPlantsUseCase: GetFilteredPlantsUseCase,
    private val updateWateredPlantUseCase: UpdateWateredPlantUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            getFilteredPlantsUseCase().collectLatest {
                state = state.copy(
                    plantListMap = it
                )
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCardLongClick -> {
                state = state.copy(
                    plant = action.plant
                )
            }
            is HomeAction.OnIconCardPlantClicked -> {
                action.plant.isWatered = !action.plant.isWatered
                viewModelScope.launch {
                    updateLoadingState(true)
                    updateWateredPlantUseCase(action.plant).fold(
                        onError = {

                        },
                        onSuccess = {

                        }
                    )
                    updateLoadingState(false)
                }
            }
            is HomeAction.OnTabClicked -> {
                state = state.copy(
                    tabSelected = TabType.entries[action.index],
                )
            }

            is HomeAction.OnDeletePlant -> {}
            else -> {}
        }
    }

    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }

}