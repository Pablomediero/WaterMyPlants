package pmediero.com.features.addplant.presentation.root

sealed class AddPlantUiEvent {
    data object NavigateToHome: AddPlantUiEvent()
}
