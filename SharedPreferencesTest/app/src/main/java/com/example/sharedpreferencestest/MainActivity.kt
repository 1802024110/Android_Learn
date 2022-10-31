package com.example.sharedpreferencestest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("姓名","小明")
            editor.putInt("年龄",18)
            editor.putBoolean("婚配",false)
            editor.apply()
        }

        val restoreButton = findViewById<Button>(R.id.restoreButton)
        restoreButton.setOnClickListener {
            // 获得SharedPreferences对象
            val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
            // 获得文件内容
            val name = prefs.getString("姓名","")
            val age = prefs.getInt("年龄",0)
            val married = prefs.getBoolean("婚配",false)
            Log.d("MainActivity","姓名是$name")
            Log.d("MainActivity","年龄是$age")
            Log.d("MainActivity","婚配是$married")
        }
    }
}