package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendRequestWithHttpUrl("https://007666.xyz/")
    }

    private fun sendRequestWithHttpUrl(url : String){
        thread {
            var connection : HttpURLConnection? = null
            try {
                val response = StringBuffer()
                val url = URL(url)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection?.disconnect()
            }
        }
    }
    private fun showResponse(response: String) {
        runOnUiThread {
            val responseText:TextView = findViewById(R.id.responseText)
            // 在这里进行UI操作，将结果显示到界面上
            responseText.text = response
        }
    }
}