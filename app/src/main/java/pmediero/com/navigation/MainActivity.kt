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
                    viewModel.checkingData.collectAsState().value,)
            }
        }
    }

    private fun initSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.checkingData.value
            }
//            setOnExitAnimationListener { screen ->
//                val zoomX = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_X,
//                    INTERPOLATION_INITIAL_VALUE,
//                    INTERPOLATION_FINAL_VALUE
//                )
//                zoomX.interpolator = OvershootInterpolator()
//                zoomX.duration = INTERPOLATION_DURATION
//                zoomX.doOnEnd { screen.remove() }
//                val zoomY = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_Y,
//                    INTERPOLATION_INITIAL_VALUE,
//                    INTERPOLATION_FINAL_VALUE
//                )
//                zoomY.interpolator = OvershootInterpolator()
//                zoomY.duration = INTERPOLATION_DURATION
//                zoomY.doOnEnd { screen.remove() }
//                zoomX.start()
//                zoomY.start()
//            }
        }
    }

    companion object {
        const val INTERPOLATION_DURATION = 500L
        const val INTERPOLATION_INITIAL_VALUE = 0.4f
        const val INTERPOLATION_FINAL_VALUE = 0.0f
    }
}




