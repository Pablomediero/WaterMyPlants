package pmediero.com.features.welcome.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.core.navigation.AppRoutes
import pmediero.com.features.welcome.action.WelcomeAction

@Composable
fun WelcomeRoot (
    navController: NavController
){
    WelcomeScreen(){ state ->
        when (state) {
            is WelcomeAction.OnAddFirstPlantClick -> {
                navController.popBackStack()
                navController.navigate(AppRoutes.AddPlantScreen.route)
            }
        }
    }
}