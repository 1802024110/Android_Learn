package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @GET("安卓第一行代码解析json数据.json")
    fun getAppData(): Call<List<App>>
}