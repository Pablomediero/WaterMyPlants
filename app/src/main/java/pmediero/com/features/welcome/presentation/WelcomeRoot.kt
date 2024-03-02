package pmediero.com.features.welcome.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.core.navigation.AppRoutes
import pmediero.com.features.welcome.event.WelcomeEvent

@Composable
fun WelcomeRoot (
    navController: NavController
){
    WelcomeScreen(){ state ->
        when (state) {
            is WelcomeEvent.OnAddFirstPlantClick -> {
                navController.popBackStack()
                navController.navigate(AppRoutes.AddPlantScreen.route)
            }
            else -> {}
        }
    }
}