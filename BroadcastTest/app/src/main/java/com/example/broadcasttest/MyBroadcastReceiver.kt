package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"在MyBroadcastReceiver接受到了信息", Toast.LENGTH_SHORT).show()
        // 截断
        abortBroadcast()
    }
}