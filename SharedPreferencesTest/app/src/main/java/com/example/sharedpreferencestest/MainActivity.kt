package com.example.sharedpreferencestest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            // 获得一个SharedPreferences对象
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("姓名","小明")
            editor.putInt("年龄",18)
            editor.putBoolean("婚配",false)
            // 保存数据
            editor.apply()
        }
    }
}