package pmediero.com.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.features.addplant.presentation.root.AddPlantRoot
import pmediero.com.features.home.presentation.root.HomeRoot
import pmediero.com.features.welcome.presentation.WelcomeRoot

@Composable
fun WaterMyPlantsNavHost(
    isPlantDataSaved: Boolean
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isPlantDataSaved) {
            AppRoutes.WelcomeScreen.route
        } else {
            AppRoutes.HomeScreen.route
        }
    ) {
        composable(AppRoutes.WelcomeScreen.route) {
            WelcomeRoot(navController = navController)
        }
        composable(AppRoutes.AddPlantScreen.route) {
            AddPlantRoot(navController = navController)
        }
        composable(AppRoutes.HomeScreen.route) {
            HomeRoot(navController = navController)
        }
    }
}