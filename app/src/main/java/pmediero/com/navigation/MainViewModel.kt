package pmediero.com.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pmediero.com.core.domain.CheckPlantExistUseCase

class MainViewModel(
    private val checkPlantExistUseCase: CheckPlantExistUseCase,
) : ViewModel() {

    private val _isPlantDataSaved = MutableStateFlow(false)
    val isPlantDataSaved = _isPlantDataSaved.asStateFlow()

    private val _checkingData = MutableStateFlow(true)
    val checkingData = _checkingData.asStateFlow()

    init {
        viewModelScope.launch {
            _isPlantDataSaved.value = checkPlantExistUseCase()
            _checkingData.value = false
        }
    }
}
