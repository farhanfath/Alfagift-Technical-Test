package gli.intern.composetechnicaltest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import gli.intern.composetechnicaltest.presentation.ui.theme.ComposeTechnicalTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTechnicalTestTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {

}
