package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getAppDataBtn:Button = findViewById(R.id.getAppDataBtn)
        getAppDataBtn.setOnClickListener {
            // 构建一个Retrofit对象
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://jsdelivr.nodream.cf/gh/1802024110/GitHub_Oss@main/file/")
                // 指定解析数据用的转换库，这里指定为gson解析json
                addConverterFactory(GsonConverterFactory.create())
            }.build()
            // 创建代理对象
            val appService = retrofit.create(AppService::class.java)
            // 异步发送请求并将其响应通知回调，或者如果发生了错误，则通知回调与服务器通信、创建请求或处理响应。
            appService.getAppData().enqueue(object : Callback<List<App>>{
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    list?.let {
                        for (app in list) {
                            Log.d("MainActivity", "id is ${app.id}")
                            Log.d("MainActivity", "name is ${app.name}")
                            Log.d("MainActivity", "version is ${app.version}")
                        }

                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) = t.printStackTrace()
            })
        }
    }
}