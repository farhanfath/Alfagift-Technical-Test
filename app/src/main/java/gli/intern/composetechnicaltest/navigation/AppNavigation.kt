package gli.intern.composetechnicaltest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.LoginScreen.name
    ) {
        composable(AppScreens.LoginScreen.name) {

        }
        composable(AppScreens.StudentListScreen.name) {

        }
    }
}