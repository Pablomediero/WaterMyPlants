package pmediero.com.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pmediero.com.core.model.local.Plant
import pmediero.com.features.home.domain.GetPlantsUseCase
import pmediero.com.features.home.presentation.root.HomeState

class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase
) : ViewModel() {

    private val _plantsState = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> = _plantsState.asStateFlow()

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
                        _plantsState.emit(plantList)
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