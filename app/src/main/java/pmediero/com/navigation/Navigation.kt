package pmediero.com.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.features.plant.presentation.addplant.root.AddPlantRoot
import pmediero.com.features.plant.presentation.detailplant.root.DetailRoot
import pmediero.com.features.plant.presentation.home.root.HomeRoot
import pmediero.com.features.plant.presentation.welcome.WelcomeRoot

@RequiresApi(Build.VERSION_CODES.O)
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
        composable(AppRoutes.DetailPlantScreen.route) {
            DetailRoot(navController = navController)
        }
        composable(AppRoutes.HomeScreen.route) {
            HomeRoot(navController = navController)
        }
    }
}

