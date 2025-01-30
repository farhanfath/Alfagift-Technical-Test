package gli.intern.composetechnicaltest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import gli.intern.composetechnicaltest.navigation.AppNavigation
import gli.intern.composetechnicaltest.presentation.ui.theme.ComposeTechnicalTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                AppNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit = {}) {
    ComposeTechnicalTestTheme(dynamicColor = false, darkTheme = false) {
        content()
    }
}
