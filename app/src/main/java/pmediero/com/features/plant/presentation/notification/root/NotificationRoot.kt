package pmediero.com.features.plant.presentation.notification.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.notification.NotificationScreen
import pmediero.com.features.plant.presentation.notification.NotificationViewModel
import pmediero.com.navigation.AppRoutes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationRoot(
    navController: NavController,
    notificationViewModel: NotificationViewModel = koinViewModel()
) {

    NotificationScreen(
        state = notificationViewModel.state,
        onAction = { action ->
            when(action) {
                is NotificationAction.OnReturnClick -> {
                    navController.popBackStack()
                }
                is NotificationAction.OnLinkTextClick -> {
                    navController.navigate(
                        "${ AppRoutes.DetailPlantScreen.route}/${action.plantIdParam}"
                    )
                }
                else -> {
                notificationViewModel.onAction(action)
            }
            }
        }
    )
}