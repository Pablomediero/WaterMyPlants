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
import pmediero.com.core.navigation.AppRoutes
import pmediero.com.features.addplant.presentation.AddPlantScreenFigma
import pmediero.com.features.splash.presentation.SplashScreen
import pmediero.com.features.welcome.presentation.WelcomeScreen
import pmediero.com.core.navigation.WaterMyPlantsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            WaterMyPlantsTheme {
                WaterMyPlantsNavHost()
            }
        }
    }
}




