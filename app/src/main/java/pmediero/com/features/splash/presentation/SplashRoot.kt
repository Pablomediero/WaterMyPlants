package pmediero.com.features.splash.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pmediero.com.core.navigation.AppRoutes
import pmediero.com.features.splash.action.SplashAction

@Composable
fun SplashRoot(
    navController: NavController
){
    SplashScreen(){state ->
        when (state) {
            is SplashAction.OnLoadingApp -> {
                navController.popBackStack()
                navController.navigate(AppRoutes.WelcomeScreen.route)
            }
        }
    }
}