package com.example.runtimepermissiontest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val makeCall = findViewById<Button>(R.id.makeCall)
        makeCall.setOnClickListener {
            // 检查是否授予打电话的权限，if中检查权限只要不是0就表示有权限
            /*checkSelfPermission接收两个参数
            * param1: 上下文Context
            * param2: 具体的权限名
            * */
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // 请求授予权限
                /*requestPermissions用于请求权限，接收三个参数
                * param1 : Activity的实例
                * param2 : 权限名数组
                * param3 ： 请求码，任意的唯一值即可
                * */
                ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                // 直接打电话
                call()
            }
        }

    }

    private fun call() {
        // 这个try主要防止卡bug权限获取失败
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    // 调用requestPermissions会触发此方法
    override fun onRequestPermissionsResult(
        // 在requestPermissions(Activity, String[]， int)中传递的请求代码
        requestCode: Int,
        // 请求的权限。
        permissions: Array<out String>,
        // 对应的权限的授予结果
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call()
                }else {
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}