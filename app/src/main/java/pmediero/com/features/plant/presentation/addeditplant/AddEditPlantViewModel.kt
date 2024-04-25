package pmediero.com.features.plant.presentation.addeditplant

import android.util.Log
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
import pmediero.com.features.plant.domain.useCase.ScheduleNotificationPlant
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantAction
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantState
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantUiEvent
import java.util.Calendar

class AddEditPlantViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val filterWateringDaysUseCase: FilterWateringDaysUseCase,
    private val addPlantUseCase: AddPlantUseCase,
    private val getPlantByIdUseCase: GetPlantByIdUseCase,
    private val scheduleNotificationPlant: ScheduleNotificationPlant
) : ViewModel() {

    var state by mutableStateOf(AddEditPlantState())
        private set
    private val _uiEvent = Channel<AddEditPlantUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _plantIdParam = savedStateHandle.get<String>("plantIdParam").takeIf { it != null }


    init {
        if (!_plantIdParam.isNullOrEmpty() && _plantIdParam != "null") {
            viewModelScope.launch {
                updateLoadingState(true)
                val plant = getPlantByIdUseCase(_plantIdParam)
                state = state.copy(
                    isEditPlant = true,
                    plantId = plant.id,
                    plantName = plant.name,
                    wateringDays = plant.wateringDays,
                    wateringTime = plant.wateringTime,
                    waterAmount = plant.waterAmount,
                    plantSize = plant.plantSize,
                    plantDescription = plant.description,
                    plantPhoto = plant.photo,
                    isPhotoSelected = plant.photo.isNotEmpty()
                )
                updateLoadingState(false)
            }
        }
    }

    fun onAction(action: AddEditPlantAction) {
        when (action) {
            is AddEditPlantAction.OnCreateEditPlantClick -> {
                viewModelScope.launch {
                    updateLoadingState(true)
                    addPlantUseCase(action.plant).fold(
                        onError = {

                        },
                        onSuccess = { plant ->
                            if ( isTodayWateringDay(plant.wateringDays) ) {
                                Log.i("AlarmManagerCheck","True Check Days")
                                if(isTimeToWaterToday(plant.wateringTime)){
                                    scheduleNotificationPlant(plant)
                                    Log.i("AlarmManagerCheck","True Check Hours ")
                                }

                            }
                            _uiEvent.send(AddEditPlantUiEvent.NavigateToHome)
                        }
                    )
                    updateLoadingState(false)
                }
            }

            is AddEditPlantAction.OnAddImageButtonClickEdit -> {
                state = state.copy(
                    plantPhoto = action.plantPhoto,
                    isPhotoSelected = true
                )

            }
            is AddEditPlantAction.OnRemoveImageButtonClick -> {
                state = state.copy(
                    plantPhoto = "",
                    isPhotoSelected = false
                )

            }

            is AddEditPlantAction.OnEditPlantNameChange -> {
                state = state.copy(
                    plantName = action.plantName
                )
            }

            is AddEditPlantAction.OnEditPlantSizeChange -> {
                state = state.copy(
                    plantSize = action.plantSize
                )
            }

            is AddEditPlantAction.OnEditPlantWaterAmountChange -> {
                state = state.copy(
                    waterAmount = action.waterAmount
                )
            }

            is AddEditPlantAction.OnEditPlantWateringDaysChange -> {
                state = state.copy(
                    wateringDays = filterWateringDaysUseCase(action.wateringDays)
                )
            }

            is AddEditPlantAction.OnEditPlantWateringTimeChange -> {
                state = state.copy(
                    wateringTime = action.wateringTime
                )
            }

            is AddEditPlantAction.OnEditPlantDescriptionChange -> {
                state = state.copy(
                    plantDescription = action.plantDescription
                )
            }

            else -> {}
        }

    }

    private fun updateLoadingState(param: Boolean) {
        state = state.copy(isLoading = param)
    }
    private fun isTimeToWaterToday(wateringTime: String): Boolean {
        val currentTime = Calendar.getInstance()
        val wateringTimeParts = wateringTime.split(":")
        val hourToWater = wateringTimeParts[0].toInt()
        val minuteToWater = wateringTimeParts[1].toInt()

        val timeToWater = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourToWater)
            set(Calendar.MINUTE, minuteToWater)
            set(Calendar.SECOND, 0)
        }

        val timeUntilWater = timeToWater.timeInMillis - currentTime.timeInMillis

        val endOfDay = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }

        val timeUntilEndOfDay = endOfDay.timeInMillis - currentTime.timeInMillis

        return timeUntilWater in 1..timeUntilEndOfDay
    }
    private fun isTodayWateringDay(wateringDays: String): Boolean {
        val currentDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val dayOfWeekString = getDayOfWeekString(currentDayOfWeek)
        return wateringDays.contains(dayOfWeekString, ignoreCase = true) || wateringDays.contains("everyday", ignoreCase = true)
    }

    private fun getDayOfWeekString(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> "Su"
            Calendar.MONDAY -> "Mo"
            Calendar.TUESDAY -> "Tu"
            Calendar.WEDNESDAY -> "We"
            Calendar.THURSDAY -> "Th"
            Calendar.FRIDAY -> "Fr"
            Calendar.SATURDAY -> "Sa"
            else -> throw IllegalArgumentException("Invalid day of week: $dayOfWeek")
        }
    }
}