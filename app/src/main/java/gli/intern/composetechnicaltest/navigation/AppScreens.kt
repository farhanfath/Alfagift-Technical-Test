package gli.intern.composetechnicaltest.navigation

enum class AppScreens {
    LoginScreen,
    StudentListScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens {
            return when (route?.substringBefore("/")) {
                LoginScreen.name -> LoginScreen
                StudentListScreen.name -> StudentListScreen
                null -> LoginScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
        }
    }
}