
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import screens.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        Scaffold {
            HomeScreen.apply {
                Content()
            }
        }
    }
}