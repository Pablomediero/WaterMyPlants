package pmediero.com.features.addplant.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AddPlantRoot(
    navController: NavController,
    addPlantViewModel: AddPlantViewModel
) {
    AddPlantScreen(
        state = addPlantViewModel.state,
        onEvent = { event ->
            when (event) {

                else -> {
                    addPlantViewModel.onEvent(event)
                }
            }
        }
    )
}