package gli.intern.composetechnicaltest.navigation

enum class AppScreens {
    LoginScreen,
    StudentLitScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens {
            return when (route?.substringBefore("/")) {
                LoginScreen.name -> LoginScreen
                StudentLitScreen.name -> StudentLitScreen
                null -> LoginScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
        }
    }
}