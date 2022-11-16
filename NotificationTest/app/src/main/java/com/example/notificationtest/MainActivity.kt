package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendNotice: Button = findViewById(R.id.sendNotice)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("normal","这是通知名字",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }else{
            Toast.makeText(this,"安卓版本过低",Toast.LENGTH_SHORT).show()
        }
        sendNotice.setOnClickListener {

            // 构建意图
            val intent = Intent(this,NotificationActivity::class.java)
            // Intent的描述和要执行的目标操作
            val pi = PendingIntent.getActivity(this,0,intent,0)

            val notification = NotificationCompat.Builder(this,"normal").apply {
                setContentTitle("这是通知标题")
                setContentText("这是通知文本")
                setSmallIcon(R.drawable.small_icon)
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                // 设置跳转意图
                setContentIntent(pi)
            }.build()
            manager.notify(1,notification)
        }
    }
}