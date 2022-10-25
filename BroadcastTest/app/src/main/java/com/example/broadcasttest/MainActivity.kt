package com.example.broadcasttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            // 构建了一个Intent对象,并把要发送的广播的值传入
            val intent = Intent("com.example.broadcasttest.MY_BROADCAST")
            // 调用Intent的setPackage()方法，并传入当前应用程序的包名
            intent.setPackage(packageName)
            // 每次传递一个给接受它广播的类
            sendOrderedBroadcast(intent,null)
        }
    }
}