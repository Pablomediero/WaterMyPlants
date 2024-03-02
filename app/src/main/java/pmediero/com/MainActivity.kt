package pmediero.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import pmediero.com.core.navigation.WaterMyPlantsNavHost
import pmediero.com.core_ui.WaterMyPlantsTheme

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




