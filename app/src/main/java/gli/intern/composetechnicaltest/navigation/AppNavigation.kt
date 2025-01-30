package gli.intern.composetechnicaltest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gli.intern.composetechnicaltest.presentation.ui.screens.LoginScreen
import gli.intern.composetechnicaltest.presentation.ui.screens.StudentListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.name
    ) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(AppScreens.StudentListScreen.name) {
            StudentListScreen(navController = navController)
        }
    }
}