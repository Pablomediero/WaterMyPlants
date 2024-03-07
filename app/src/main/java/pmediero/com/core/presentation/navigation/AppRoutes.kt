package pmediero.com.core.presentation.navigation

sealed class AppRoutes(val route: String){
    object SplashScreen: AppRoutes("splash_screen")
    object WelcomeScreen: AppRoutes("welcome_screen")
    object AddPlantScreen: AppRoutes("add_plant_screen")
    object HomeScreen: AppRoutes("home_screen")
}