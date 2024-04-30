package pmediero.com.features.plant.presentation.notification

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pmediero.com.features.plant.domain.useCase.GetFilteredNotificationsUseCase
import pmediero.com.features.plant.presentation.notification.root.NotificationAction
import pmediero.com.features.plant.presentation.notification.root.NotificationState

@RequiresApi(Build.VERSION_CODES.O)
class NotificationViewModel(
    private val getFilteredNotificationsUseCase: GetFilteredNotificationsUseCase,

    ):ViewModel() {

    var state by mutableStateOf(NotificationState())
        private set

    init {
        viewModelScope.launch {
            updateLoadingState(true)
            getFilteredNotificationsUseCase().collectLatest {
                state = state.copy(
                    plantListMap = it
                )
                updateLoadingState(false)
            }

        }
    }
    fun onAction(action: NotificationAction){
        when (action){

            else -> {}
        }
    }
    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }

}