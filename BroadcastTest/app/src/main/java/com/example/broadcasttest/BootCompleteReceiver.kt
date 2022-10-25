package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        //此方法在BroadcastReceiver接收Intent广播时被调用。
        Toast.makeText(context,"Boot Complete", Toast.LENGTH_SHORT).show()
    }
}