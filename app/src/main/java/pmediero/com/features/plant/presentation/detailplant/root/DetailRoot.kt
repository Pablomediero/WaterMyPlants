package pmediero.com.features.plant.presentation.detailplant.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.detailplant.DetailScreen
import pmediero.com.features.plant.presentation.detailplant.DetailViewModel
import pmediero.com.navigation.AppRoutes

@Composable
fun DetailRoot(
    navController: NavController,
    detailViewModel: DetailViewModel = koinViewModel()
) {

    DetailScreen(
        state = detailViewModel.state,
        onAction = { action ->
            when (action) {
                is DetailAction.OnReturnClick -> {
                    navController.popBackStack()
                }

                is DetailAction.OnEditButtonClick -> {
                    navController.navigate(
                        "${AppRoutes.AddEditPlantScreen.route}/${action.plantIdParam}"
                    )

                }

                else -> {
                    detailViewModel.onAction(action)
                }
            }
        }
    )
}