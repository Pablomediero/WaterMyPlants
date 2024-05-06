package pmediero.com.features.plant.presentation.welcome.root

sealed class WelcomeAction {
    data object OnAddFirstPlantClick: WelcomeAction()
}