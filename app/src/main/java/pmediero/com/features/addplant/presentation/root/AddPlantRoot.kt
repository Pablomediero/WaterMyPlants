package pmediero.com.features.addplant.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import pmediero.com.core.presentation.navigation.AppRoutes
import pmediero.com.features.addplant.presentation.AddPlantScreen
import pmediero.com.features.addplant.presentation.viewmodel.AddPlantViewModel

@Composable
fun AddPlantRoot(
    navController: NavController,
    addPlantViewModel: AddPlantViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    AddPlantScreen(
        state = addPlantViewModel.state,
        onEvent = { event ->
            when (event) {
                OnCreatePlant -> {
                    navController.popBackStack()
                    navController.navigate(AppRoutes.HomeScreen.route)
                }

                else -> {
                    coroutineScope.launch {
                        addPlantViewModel.onEvent(event)
                    }
                }
            }
        }
    )
}