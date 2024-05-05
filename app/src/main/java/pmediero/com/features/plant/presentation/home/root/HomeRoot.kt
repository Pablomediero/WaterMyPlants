package pmediero.com.features.plant.presentation.home.root

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.plant.presentation._common.NotificationPermissionTextProvider
import pmediero.com.features.plant.presentation._common.PermissionDialog
import pmediero.com.features.plant.presentation.home.HomeScreen
import pmediero.com.features.plant.presentation.home.HomeViewModel
import pmediero.com.navigation.AppRoutes

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SharedTransitionScope.HomeRoot(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val context = LocalContext.current
    val activity = context as Activity
    var showPermissionDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val notificationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (!isGranted) {
                showPermissionDialog = true
            }
        }
    )
    LaunchedEffect(key1 = Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (showPermissionDialog)
            PermissionDialog(
                permissionTextProvider = NotificationPermissionTextProvider(),
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                ),
                onDismiss = { showPermissionDialog = false },
                onOkClick = {
                    showPermissionDialog = false
                    notificationPermissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                },
                onGoToAppSettingsClick = { activity.openAppSettings() }
            )
    }
    HomeScreen(
        state = homeViewModel.state,
        onAction = { action ->
            when (action) {
                is HomeAction.NavigateAddPlant -> {
                    navController.navigate("${AppRoutes.AddEditPlantScreen.route}/${null}")
                }
                is HomeAction.NavigateNotification -> {
                    navController.navigate(AppRoutes.NotificationScreen.route)
                }

                is HomeAction.OnClickPlant -> {
                    navController.navigate(
                       "${ AppRoutes.DetailPlantScreen.route}/${action.idPlantParam}"
                    )
                }

                else -> {
                    homeViewModel.onAction(action)
                }
            }
        },
        animatedVisibilityScope = animatedVisibilityScope
    )
}
fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}