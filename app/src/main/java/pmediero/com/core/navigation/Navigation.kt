package pmediero.com.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.features.addplant.presentation.AddPlantRoot
import pmediero.com.features.splash.presentation.SplashRoot
import pmediero.com.features.welcome.presentation.WelcomeRoot

@Composable
fun WaterMyPlantsNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route) {
        composable( AppRoutes.SplashScreen.route){
            SplashRoot(navController = navController)
        }
        composable( AppRoutes.WelcomeScreen.route){
            WelcomeRoot(navController = navController)
        }
        composable( AppRoutes.AddPlantScreen.route){
            AddPlantRoot(navController = navController)
        }
    }
}