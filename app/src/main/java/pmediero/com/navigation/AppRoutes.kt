package pmediero.com.navigation

sealed class AppRoutes(val route: String){
    data object WelcomeScreen: AppRoutes("welcome_screen")
    data object AddEditPlantScreen: AppRoutes("add_plant_screen")
    data object DetailPlantScreen: AppRoutes("detail_plant_screen")
    data object HomeScreen: AppRoutes("home_screen")
}