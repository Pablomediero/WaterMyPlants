package pmediero.com.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import pmediero.com.R

@Composable
fun SplashScreen(navController: NavController){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.plantexampleanimation))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(key1 = true) {
        delay(4000L)
       navController.popBackStack()
       navController.navigate("welcome_screen")
    }
    Column (
        modifier = Modifier.fillMaxSize().background(
            if (isSystemInDarkTheme()) Color.DarkGray
            else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }

}

