package pmediero.com.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.features.addplant.presentation.AddPlantScreenFigma
import pmediero.com.features.splash.SplashScreen
import pmediero.com.features.welcome.WelcomeScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route) {
        composable( Routes.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable( Routes.WelcomeScreen.route){
            WelcomeScreen(navController = navController)
        }
        composable( Routes.AddPlantScreen.route){
            AddPlantScreenFigma()
        }
    }
}