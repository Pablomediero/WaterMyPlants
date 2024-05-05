package pmediero.com.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import pmediero.com.features.plant.presentation.addeditplant.root.AddEditPlantRoot
import pmediero.com.features.plant.presentation.detailplant.root.DetailRoot
import pmediero.com.features.plant.presentation.home.root.HomeRoot
import pmediero.com.features.plant.presentation.notification.root.NotificationRoot
import pmediero.com.features.plant.presentation.welcome.WelcomeRoot

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterMyPlantsNavHost(
    isPlantDataSaved: Boolean,
    checkingData: Boolean
) {
    val navController = rememberNavController()
    if(!checkingData){
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
            composable(
                route = "${AppRoutes.AddEditPlantScreen.route}/{plantIdParam}",
                arguments = listOf(navArgument("plantIdParam") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = "0"
                }),
            ) {
                AddEditPlantRoot(navController = navController)
            }
            composable(
                route = "${AppRoutes.DetailPlantScreen.route}/{plantIdParam}",
                arguments = listOf(navArgument("plantIdParam") {
                    type = NavType.StringType
                }),
                deepLinks = listOf(
                    navDeepLink { uriPattern = "watermyplants://detail/{plantIdParam}" }
                )
            ) {
                DetailRoot(navController = navController)
            }
            composable(AppRoutes.HomeScreen.route) {
                HomeRoot(navController = navController)
            }
            composable(AppRoutes.NotificationScreen.route) {
                NotificationRoot(navController = navController)
            }
        }

    }
}
