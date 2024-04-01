package pmediero.com.navigation

sealed class AppRoutes(val route: String){
    object WelcomeScreen: AppRoutes("welcome_screen")
    object AddPlantScreen: AppRoutes("add_plant_screen")
    object HomeScreen: AppRoutes("home_screen")
}