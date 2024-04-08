package pmediero.com.features.plant.presentation.welcome

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.features.plant.presentation.welcome.action.WelcomeAction
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