package pmediero.com.features.routes

sealed class AppRoutes(val route: String){
    object SplashScreen: AppRoutes("splash_screen")
    object WelcomeScreen: AppRoutes("welcome_screen")
    object AddPlantScreen: AppRoutes("add_plant_screen")
}