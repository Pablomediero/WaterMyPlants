package pmediero.com.navigation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject
import pmediero.com.core_ui.WaterMyPlantsTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        setContent {
            WaterMyPlantsTheme {
                WaterMyPlantsNavHost(
                    viewModel.isPlantDataSaved.collectAsState().value,
                    viewModel.checkingData.collectAsState().value,
                )
            }
        }
    }

    private fun initSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.checkingData.value
            }
        }
    }
}




