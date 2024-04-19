package pmediero.com.features.plant.presentation.addplant.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.addplant.AddPlantScreen
import pmediero.com.features.plant.presentation.addplant.AddPlantViewModel
import pmediero.com.navigation.AppRoutes

@Composable
fun AddEditPlantRoot(
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