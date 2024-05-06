package pmediero.com.features.plant.presentation.welcome.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.features.plant.presentation.welcome.WelcomeScreen
import pmediero.com.navigation.AppRoutes

@Composable
fun WelcomeRoot (
    navController: NavController
){
    WelcomeScreen(){ state ->
        when (state) {
            is WelcomeAction.OnAddFirstPlantClick -> {
                navController.navigate("${AppRoutes.AddEditPlantScreen.route}/${null}")
            }
        }
    }
}