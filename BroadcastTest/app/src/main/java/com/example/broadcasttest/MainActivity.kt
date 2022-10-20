package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var timeChangeReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 创建了IntentFilter实例，用于匹配响应的广播
        val intentFilter = IntentFilter()
        // 且添加了值为如下的参数，接收该类型的广播
        intentFilter.addAction("android.intent.action.TIME_TICK")
        // 然后实例化了TimeChangeReceiver
        timeChangeReceiver = TimeChangeReceiver()
        // 注册广播
        registerReceiver(timeChangeReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 当Activity被销毁时，注销监听广播
        unregisterReceiver(timeChangeReceiver)
    }
    // 定义的内部类，继承于BroadcastReceiver接收广播
    inner class TimeChangeReceiver:BroadcastReceiver() {
        // 当接收到广播时触发该方法
        override fun onReceive(context: Context?, intent: Intent?) {
            // 简单的消息示意
            Toast.makeText(context,"时间已经改变",Toast.LENGTH_SHORT).show()
        }
    }
}