package com.example.mysviewpage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log.d
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    // 使用主线程的循环器
    private val myHandler = Handler(Looper.getMainLooper())

    private val datas = ArrayList<Int>().apply {
        add(R.drawable.tx1)
        add(R.drawable.tx2)
        add(R.drawable.tx3)
        add(R.drawable.tx4)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPage2 = findViewById<ViewPager2>(R.id.viewpager2)
        val myViewPage2Adapter = MyViewPage2Adapter(datas)
        viewPage2.adapter = myViewPage2Adapter
    }

    private val runnable:Runnable = object : Runnable{
        override fun run() {
            TODO("Not yet implemented")
        }
    }
}