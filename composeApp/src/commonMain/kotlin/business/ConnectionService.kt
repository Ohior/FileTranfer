package business

import io.ktor.http.ContentType
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import utils.Constants

class ConnectionService {
    private val port = 8080
    private val host = "192.168.43.1"
    private var applicationEngine: ApplicationEngine? = null

    suspend fun startServer() {
        println("starting address at http://${host}:${port}")
        applicationEngine = embeddedServer(CIO, port = port, host = host) {
            routing {
                get("/") {
                    call.respondText(
                        text = Constants.homePage(),
                        contentType = ContentType.Text.Html
                    )
                }
                get("/download/{name}") {
                    val name = call.parameters["name"]
                    println("DOWNLOAD -> $name")
                }
                post("/upload") {
                    try {
//            // get the data been pass the server by the form
                        val multipartData = call.receiveMultipart()
                        withContext(Dispatchers.IO) {
                            multipartData.forEachPart { part ->
                                // check which data part is
                                when (part) {
                                    is PartData.FileItem -> {
                                        // upload the data
                                        val inputStream = part.provider
                                        val fileBytes = inputStream.asFlow()
//                                            val mFile = File(Const.SETTING_UPLOAD_PATH + part.originalFileName)
//                                            mFile.writeBytes(fileBytes)
                                    }

                                    else -> Unit
                                }
                                part.dispose()
                            }
                        }
                    } catch (e: Exception) {
                        call.respondRedirect("web")

                    }
                }
            }
        }.start(wait = true)
    }

    fun stopServer() {
        applicationEngine?.stop()
    }
}