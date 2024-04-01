package pmediero.com.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pmediero.com.features.home.domain.GetPlantsUseCase
import pmediero.com.features.home.presentation.model.TabType
import pmediero.com.features.home.presentation.root.HomeAction
import pmediero.com.features.home.presentation.root.HomeState

class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            getPlantsUseCase().fold(
                onError = {

                },
                onSuccess = {
                    updateLoadingState(true)
                    it.collect { plantList ->
                        state = state.copy(
                            plantList = plantList
                        )
                    }
                    updateLoadingState(false)
                }
            )

        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCardLongClick -> {
                state = state.copy(
                    plant = action.plant
                )
            }

            is HomeAction.OnTabClicked -> {
                state = state.copy(
                    tabSelected = TabType.entries[action.index]

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