package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        val sendRequestBtn: Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            sendRequestWithHttpUrl("https://007666.xyz/")
        }
    }

    private fun sendRequestWithHttpUrl(url : String){
        thread {
            // 预定义一个HttpURLConnection对象，方便下边调用
            var connection : HttpURLConnection? = null
            try {
                // 字符缓冲区，用于保存返回内容
                val response = StringBuffer()
                // 构建一个Url对象
                val url = URL(url)
                // 实例HttpURLConnection对象
                connection = url.openConnection() as HttpURLConnection
                // 设置连接超时时间
                connection.connectTimeout = 8000
                // 设置读取超时时间
                connection.readTimeout = 8000
                // 打开输入流
                val input = connection.inputStream
                // 创建使用默认大小的输入缓冲区的缓冲字符输入流。
                val reader = BufferedReader(InputStreamReader(input))

                reader.use {
                    // 遍历
                    reader.forEachLine {
                        // 添加到缓冲区
                        response.append(it)
                    }
                }
                // 调用showResponse
                showResponse(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                // 关闭链接
                connection?.disconnect()
            }
        }
    }
    private fun showResponse(response: String) {
        // 在UI线程上运行指定的操作。如果当前线程是UI线程，那么动作将立即执行。
        runOnUiThread {
            val responseText:TextView = findViewById(R.id.responseText)
            // 在这里进行UI操作，将结果显示到界面上
            responseText.text = response
        }
    }
}