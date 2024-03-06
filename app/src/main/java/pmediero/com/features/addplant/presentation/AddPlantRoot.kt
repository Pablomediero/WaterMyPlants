package pmediero.com.features.addplant.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddPlantRoot(
    navController: NavController,
    addPlantViewModel: AddPlantViewModel = koinViewModel()
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