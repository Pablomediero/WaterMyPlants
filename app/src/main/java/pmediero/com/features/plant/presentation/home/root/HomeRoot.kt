package pmediero.com.features.plant.presentation.home.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.home.HomeScreen
import pmediero.com.features.plant.presentation.home.HomeViewModel
import pmediero.com.navigation.AppRoutes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeRoot(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    HomeScreen(
        state = homeViewModel.state,
        onAction = { action ->
            when (action) {
                is HomeAction.NavigateAddPlant -> {
                    navController.navigate(AppRoutes.AddPlantScreen.route)
                }

                else -> {
                    homeViewModel.onAction(action)
                }
            }
        }
    )
}