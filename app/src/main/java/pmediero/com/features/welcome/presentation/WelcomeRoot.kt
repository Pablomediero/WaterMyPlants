package pmediero.com.features.welcome.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.features.welcome.action.WelcomeAction
import pmediero.com.navigation.AppRoutes

@Composable
fun WelcomeRoot (
    navController: NavController
){
    WelcomeScreen(){ state ->
        when (state) {
            is WelcomeAction.OnAddFirstPlantClick -> {
                navController.navigate(AppRoutes.AddPlantScreen.route)
            }
        }
    }
}