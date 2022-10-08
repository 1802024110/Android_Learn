package com.example.uiconstomviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context,attrs){
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
         val titleBack:Button = findViewById(R.id.titleBack)
        titleBack.setOnClickListener{
            // 将Context转为Activity类型
            val activity = context as Activity
            activity.finish()
        }
        // 关键代码开始
        val titleEdit = findViewById<Button>(R.id.titleEdit)
        titleEdit.setOnClickListener{
            Toast.makeText(context,"你点击了编辑按钮",Toast.LENGTH_LONG).show()
        }
        // 关键代码结束
    }
}