package pmediero.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.routes.AppRoutes
import pmediero.com.features.screens.addplant.AddPlantScreenFigma
import pmediero.com.features.screens.splash.SplashScreen
import pmediero.com.features.screens.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMyPlantsTheme {
                Navigation()
            }
        }
    }

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
}




