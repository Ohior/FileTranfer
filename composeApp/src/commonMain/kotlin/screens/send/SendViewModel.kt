package screens.send

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import business.ConnectionService
import kotlinx.coroutines.launch

class SendViewModel : ViewModel() {
    private val connectionService = ConnectionService()

    var serverActive by mutableStateOf(false)  // Use underscore for private backing field
    var showFilePicker by mutableStateOf(false)


    fun startServer() {
        viewModelScope.launch {
            serverActive = true
            connectionService.startServer()
        }
    }

    fun stopServer() {
        connectionService.stopServer()
        serverActive = false
    }
}