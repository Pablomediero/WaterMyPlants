package pmediero.com.features.plant.presentation.addeditplant.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.addeditplant.AddEditPlantScreen
import pmediero.com.features.plant.presentation.addeditplant.AddEditPlantViewModel
import pmediero.com.navigation.AppRoutes

@Composable
fun AddEditPlantRoot(
    navController: NavController,
    addEditPlantViewModel: AddEditPlantViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = true) {
        addEditPlantViewModel.uiEvent.collect { event ->
            when (event) {
                is AddEditPlantUiEvent.NavigateToHome -> {
                    navController.navigate(AppRoutes.HomeScreen.route)
                }
            }
        }
    }
    AddEditPlantScreen(
        state = addEditPlantViewModel.state,
        onAction = { action ->
            when (action) {
                else -> {
                    addEditPlantViewModel.onAction(action)
                }
            }
        }
    )
}