package pmediero.com.features.addplant.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.core.presentation.navigation.AppRoutes
import pmediero.com.features.addplant.presentation.AddPlantScreen
import pmediero.com.features.addplant.presentation.AddPlantViewModel

@Composable
fun AddPlantRoot(
    navController: NavController,
    addPlantViewModel: AddPlantViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = true) {
        addPlantViewModel.uiEvent.collect { event ->
            when (event) {
                is AddPlantUiEvent.NavigateToHome -> {
                    navController.navigate(AppRoutes.HomeScreen.route)
                    }
            }
        }
    }
    AddPlantScreen(
        state = addPlantViewModel.state,
        onAction = { action ->
            when (action) {
                else -> {
                    addPlantViewModel.onAction(action)
                }
            }
        }
    )
}