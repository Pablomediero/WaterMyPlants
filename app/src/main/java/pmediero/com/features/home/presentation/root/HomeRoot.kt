package pmediero.com.features.home.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.home.presentation.HomeScreen
import pmediero.com.features.home.presentation.HomeViewModel

@Composable
fun HomeRoot(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    HomeScreen(
        viewModel = homeViewModel,
        onEvent = { event ->
            when (event) {
                else -> {
                    coroutineScope.launch {
                        homeViewModel.onEvent(event)
                    }
                }
            }

        }
    )
}