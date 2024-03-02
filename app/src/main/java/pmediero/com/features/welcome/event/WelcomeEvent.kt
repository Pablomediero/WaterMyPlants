package pmediero.com.features.welcome.event

sealed class WelcomeEvent {
    object OnAddFirstPlantClick: WelcomeEvent()
}