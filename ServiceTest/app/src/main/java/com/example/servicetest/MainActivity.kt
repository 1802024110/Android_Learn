package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.SystemClock
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // 自己定义的下载服务
    lateinit var downloadBinder:MyService.DownloadBinding
    // 监视应用程序服务状态的接口，这个类上的方法是从进程的主线程调用的。
    private val connection = object:ServiceConnection{
        // 当service与服务的连接已经建立时调用
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 将传入的service转为MyService.DownloadBinding
            downloadBinder = service as MyService.DownloadBinding
            // 执行下载
            downloadBinder.startDownload()
            // 执行进度条
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startServiceBtn:Button = findViewById(R.id.startServiceBtn)
        val stopServiceBtn:Button = findViewById(R.id.stopServiceBtn)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        val bindServiceBtn:Button = findViewById(R.id.bindServiceBtn)
        val unbindServiceBtn:Button = findViewById(R.id.unbindServiceBtn)

        bindServiceBtn.setOnClickListener {
            // 构建一个意图
            val intent = Intent(this,MyService::class.java)
            // 绑定Service
            bindService(intent,connection, Context.BIND_AUTO_CREATE)
        }
        unbindServiceBtn.setOnClickListener {
            // 解除Service
            unbindService(connection)
        }
    }
}