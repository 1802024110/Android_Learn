package com.example.mysviewpage2
import kotlinx.coroutines.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
//    在主线程创建循环器
    private var mHandler = Handler(Looper.getMainLooper())
    private lateinit var viewPage2:ViewPager2
    private val datas = ArrayList<Int>().apply {
        add(R.drawable.tx1)
        add(R.drawable.tx2)
        add(R.drawable.tx3)
        add(R.drawable.tx4)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPage2 = findViewById<ViewPager2>(R.id.viewpager2)
        val myViewPage2Adapter = MyViewPage2Adapter(datas)
        viewPage2.adapter = myViewPage2Adapter
    }

//    软件启动时回调
    override fun onResume(){
        super.onResume()
    mHandler.postDelayed(runnable,500)
}
//    退出时移除循环器
    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(runnable)
    }

    private val runnable: java.lang.Runnable = object : java.lang.Runnable {
        override fun run() {
            //获得轮播图当前的位置
            var currentPosition = viewPage2.currentItem
            currentPosition++
            viewPage2.setCurrentItem(currentPosition, true)
            mHandler.postDelayed(this, 5000)
        }
    }
}