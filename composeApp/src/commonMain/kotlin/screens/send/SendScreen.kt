package screens.send

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darkrockstudios.libraries.mpfilepicker.MultipleFilePicker
import ui.mediumDpSize

object SendScreen {
    @Composable
    fun Content(sendViewModel: SendViewModel = viewModel { SendViewModel() }) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(mediumDpSize)
        ) {
            Column(modifier = Modifier.fillMaxWidth().weight(1f).background(Color.Yellow)) { }
            Button(onClick = { sendViewModel.showFilePicker = true }) { Text("Pick File") }
            Button(onClick = {
                if (sendViewModel.serverActive) {
                    sendViewModel.stopServer()
                } else {
                    sendViewModel.stopServer()
                }
                sendViewModel.serverActive = !sendViewModel.serverActive
            }, modifier = Modifier.fillMaxWidth().padding(horizontal = mediumDpSize)) {
                Text(if (sendViewModel.serverActive) "Stop Server" else "Start Server")
            }
            if (sendViewModel.showFilePicker) {
                pickFile(sendViewModel)
            }
        }
    }

    @Composable
    fun pickFile(sendViewModel: SendViewModel) {
        val fileType = listOf("jpg", "png", "mp4", "mp3", "txt", "pdf")
        MultipleFilePicker(
            show = sendViewModel.showFilePicker,
            fileExtensions = fileType
        ) { platformFile ->
            sendViewModel.showFilePicker = false
            // do something with the file
            if (platformFile != null) {
                for (file in platformFile) {
                    println(
                        """
                        platformFile : ${file.platformFile}"
                        path : ${file.path}"
                        """.trimIndent()
                    )
                }
            }

        }
    }
}