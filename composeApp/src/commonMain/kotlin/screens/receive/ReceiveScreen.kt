package screens.receive

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import utils.Constants

object ReceiveScreen {
    @Composable
    fun Content() {
        val webViewState = rememberWebViewStateWithHTMLData(data = Constants.homePage())
        WebView(state = webViewState, modifier = Modifier.fillMaxSize())
    }
}