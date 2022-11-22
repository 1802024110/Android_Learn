package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendRequestBtn: Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            sendRequestWithHttpUrl("https://jsdelivr.nodream.cf/gh/1802024110/GitHub_Oss@main/file/安卓第一行代码解析json数据.json")
        }
    }

    private fun sendRequestWithHttpUrl(url : String){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().apply {
                    url(url)
                }.build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                responseData?.let {
                    parseJSONWithJSONObject(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        try {
            // 关键代码开始
            val gson = Gson()
            val typeOf = object:TypeToken<List<App>>() {}.type
            val appList = gson.fromJson<List<App>>(jsonData,typeOf)
            for (app in appList) {
                Log.d("MainActivity", "id is ${app.id}")
                Log.d("MainActivity", "name is ${app.name}")
                Log.d("MainActivity", "version is ${app.version}")
            }
            // 关键代码结束
        }catch (e: Exception){
            e.printStackTrace()
        }
    }


}