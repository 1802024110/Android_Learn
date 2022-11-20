package com.example.servicetest

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {
    private val mBinding = DownloadBinding()
    inner class DownloadBinding : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }
        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }
    override fun onBind(intent: Intent): IBinder {
        return mBinding
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("MyService", "onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("我的Service","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel )
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this,"我的Service").apply {
            setContentTitle("这是内容标题")
            setContentText("这是内容")
            setSmallIcon(R.drawable.small_icon)
            setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon))
            setContentIntent(pi)
        }.build()
        startForeground(1,notification)

    }
}