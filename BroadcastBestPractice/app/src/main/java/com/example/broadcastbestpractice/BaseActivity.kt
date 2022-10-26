package com.example.broadcastbestpractice

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    // 预定义BroadcastReceiver对象，该类用于接收和处理Context.sendBroadcast(Intent)发送的广播意图的代码的基类。
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    // 被激活时触发
    override fun onResume() {
        super.onResume()
        // 初始化intent
        val intentFilter = IntentFilter()
        // 添加一个广播
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE")
        // 实例化广播
        receiver = ForceOfflineReceiver()
        // 注册广播
        registerReceiver(receiver,intentFilter)
    }

    // 暂停时激活
    override fun onPause() {
        super.onPause()
        // 卸载广播
        unregisterReceiver(receiver)
    }

    // 该内部类继承BroadcastReceiver
    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // 创建一个弹窗
            AlertDialog.Builder(context).apply {
                setTitle("警告")
                setMessage("你的账号被强制下线，请重新登录")
                // 不让取消
                setCancelable(false)
                // 设置确认按钮字符为ok
                setPositiveButton("OK"){_,_->
                    // 关闭所有Activity
                    ActivityCollector.finishAll()
                    val  i = Intent(context, LoginActivity::class.java)
                    // 启动
                    context?.startActivity(i)
                }
                show()
            }
        }
    }
}