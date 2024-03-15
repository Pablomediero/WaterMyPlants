package pmediero.com.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pmediero.com.features.home.domain.GetPlantsUseCase
import pmediero.com.features.home.presentation.root.HomeState

class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            getPlantsUseCase.invoke().fold(
                onError = {

                },
                onSuccess = {
                    updateLoadingState(true)
                    it.collect { plantList ->
                        //_plantsState.emit(plantList)
                        state = state.copy(
                            plantList = plantList
                        )
                    }
                    updateLoadingState(false)
                }
            )

        }
    }

    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }
}