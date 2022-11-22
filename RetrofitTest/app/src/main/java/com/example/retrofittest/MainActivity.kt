package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getAppDataBtn:Button = findViewById(R.id.getAppDataBtn)
        getAppDataBtn.setOnClickListener {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://jsdelivr.nodream.cf/gh/1802024110/GitHub_Oss@main/file/")
            }
        }
    }
}