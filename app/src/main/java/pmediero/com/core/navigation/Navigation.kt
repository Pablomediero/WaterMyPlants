package pmediero.com.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.core.navigation.AppRoutes
import pmediero.com.features.addplant.presentation.AddPlantScreenFigma
import pmediero.com.features.splash.presentation.SplashScreen
import pmediero.com.features.welcome.presentation.WelcomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route) {
        composable( AppRoutes.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable( AppRoutes.WelcomeScreen.route){
            WelcomeScreen(navController = navController)
        }
        composable( AppRoutes.AddPlantScreen.route){
            AddPlantScreenFigma()
        }
    }
}