package pmediero.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmediero.com.core_ui.WaterMyPlantsTheme
import pmediero.com.features.splash.SplashScreen
import pmediero.com.features.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
            startDestination = "splash_screen") {
            composable("splash_screen"){
                SplashScreen(navController = navController)
            }
            composable("welcome_screen"){
                WelcomeScreen()
            }
        }
    }
}




