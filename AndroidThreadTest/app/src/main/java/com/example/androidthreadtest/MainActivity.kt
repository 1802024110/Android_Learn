package com.example.androidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    // 消息代码
    private val updateText = 1
    private lateinit var changeTextBtn: Button
    private lateinit var textView: TextView

    private val handler = object : Handler(Looper.getMainLooper()){
        // handler子类必须实现这个来接收消息。
        override fun handleMessage(msg: Message) {
            // 判断传来的消息代码
            when (msg.what) {
                updateText -> textView.text = "Nice to meet you"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeTextBtn = findViewById(R.id.changeTextBtn)
        textView = findViewById(R.id.textView)

        changeTextBtn.setOnClickListener {
            thread {
                // 定义包含描述和可发送到处理程序的任意数据对象的消息
                val msg = Message()
                // 用户定义的消息代码，以便接收方能够识别此消息的内容。
                msg.what = updateText
                // 将消息推到消息队列的末尾,它将在附加到此处理程序的线程中的handleMessage中接收。
                handler.sendMessage(msg)
            }
        }
    }
}