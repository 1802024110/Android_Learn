package com.example.b_4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttn1 = findViewById<Button>(R.id.button1)
        buttn1.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button1->{
                // 开始
                AlertDialog.Builder(this).apply {
                    setTitle("这是一个弹窗")
                    setMessage("这是弹窗内容")
                    // 是否可以用返回键关闭对话框
                    setCancelable(false)
                    // 确认事件
                    setPositiveButton("确认",{dialog,which->})
                    // 取消事件
                    setNegativeButton("关闭",{dialog,which->})
                }.show()
                // 结束
            }
        }
    }
}