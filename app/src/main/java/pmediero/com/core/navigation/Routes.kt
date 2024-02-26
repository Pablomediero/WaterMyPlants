package pmediero.com.core.navigation

sealed class Routes(val route: String){
    object SplashScreen: Routes("splash_screen")
    object WelcomeScreen: Routes("welcome_screen")
    object AddPlantScreen: Routes("add_plant_screen")
}