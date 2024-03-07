package pmediero.com.features.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pmediero.com.core.model.realm.PlantEntity
import pmediero.com.features.home.domain.GetPlantsUseCase
import pmediero.com.features.home.presentation.root.HomeAction
import pmediero.com.features.home.presentation.root.OnLoadingHome

class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase
) : ViewModel() {

    private val _plantsState = MutableStateFlow<List<PlantEntity>>(emptyList())
    val plants: StateFlow<List<PlantEntity>> = _plantsState.asStateFlow()

    suspend fun onEvent(event: HomeAction) {
        when (event) {
            OnLoadingHome -> {
                getPlantsUseCase.invoke()
                    .collect { plantList ->
                        _plantsState.emit(plantList)
                    }
            }

            else -> {}
        }
    }
}