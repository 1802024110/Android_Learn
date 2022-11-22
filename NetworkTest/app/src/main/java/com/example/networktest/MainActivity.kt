package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request
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
            try {
                // 创建OkHttpClient
                val client = OkHttpClient()
                // 创建构造器
                val request = Request.Builder().apply {
                    url(url)
                }.build()
                // 发送请求
                val response = client.newCall(request).execute()
                // 获得返回值
                val responseData = response.body?.string()
                // 判断是否为空
                responseData?.let {
                    // 渲染
                    showResponse(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    private fun showResponse(response: String) {
        runOnUiThread {
            val responseText:TextView = findViewById(R.id.responseText)
            responseText.text = response
        }
    }
}