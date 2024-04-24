package pmediero.com.features.plant.presentation.notification.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation.notification.NotificationScreen
import pmediero.com.features.plant.presentation.notification.NotificationViewModel

@Composable
fun NotificationRoot(
    navController: NavController,
    notificationViewModel: NotificationViewModel = koinViewModel()
) {

    NotificationScreen(
        onAction = { action ->
            when(action) {
                is NotificationAction.OnReturnClick -> {
                    navController.popBackStack()
                }
                else -> {
                notificationViewModel.onAction(action)
            }
            }
        }
    )
}