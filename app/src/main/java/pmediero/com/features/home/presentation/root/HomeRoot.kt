package pmediero.com.features.home.presentation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import pmediero.com.features.home.presentation.HomeScreen
import pmediero.com.features.home.presentation.HomeViewModel

@Composable
fun HomeRoot(
    navController: NavController,
    homeViewModel: HomeViewModel = koinViewModel()
) {

    HomeScreen(
        viewModel = homeViewModel,
        onAction = { }
    )
}