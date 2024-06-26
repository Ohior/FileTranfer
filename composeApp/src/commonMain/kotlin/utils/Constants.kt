package utils

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.body
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.onClick
import kotlinx.html.script
import kotlinx.html.stream.createHTML
import kotlinx.html.style
import kotlinx.html.title

object Constants {
    fun homePage(): String {
        return createHTML().html {
            head {
                title("Web Page")
            }
            body {
                style = """
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f9;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                """.trimIndent()
                div {
                    style = """
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            justify-content: center;
                            padding: 20px;
                            background-color: #ffffff;
                            border-radius: 8px;
                            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    """.trimIndent()
                    h2 {
                        style = """
                                color: #333;
                                margin-bottom: 20px;
                        """.trimIndent()
                        +"Select files to upload"
                    }
                    input(
                        type = InputType.file,
                    ) {
                        multiple = true
                        style = """
                                display: inline-block;
                                padding: 10px 20px;
                                background-color: #007bff;
                                color: white;
                                border: none;
                                border-radius: 4px;
                                cursor: pointer;
                                transition: background-color 0.3s;
                        """.trimIndent()
                        id = "fileInput"
                    }
                    button(type = ButtonType.button) {
                        style = """
                                padding: 10px 20px;
                                background-color: #28a745;
                                color: white;
                                border: none;
                                border-radius: 4px;
                                cursor: pointer;
                                transition: background-color 0.3s;
                        """.trimIndent()
                        onClick = "uploadFiles()"
                        +"Send File(s)"
                    }
                }
                script(type = "text/javascript") {
                    +"""
                    function uploadFiles() {
                        const fileInput = document.getElementById('fileInput');
                        const files = fileInput.files;

                        if (files.length === 0) {
                          alert('Please select at least one file.');
                          return;
                        }

                        const formData = new FormData();
                        for (let i = 0; i < files.length; i++) {
                          formData.append('files[]', files[i]);
                        }

                        fetch('/upload', {
                          method: 'POST',
                          body: formData
                        })
                        .then(response => {
                          if (response.ok) {
                            alert('Files uploaded successfully.');
                          } else {
                            alert('Error uploading files.');
                          }
                        })
                        .catch(error => {
                          console.error('Error uploading files:', error);
                          alert('Error uploading files.');
                        });
                    }
                    """.trimIndent()
                }
            }
        }
    }
}