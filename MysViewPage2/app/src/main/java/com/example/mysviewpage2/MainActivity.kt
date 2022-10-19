package com.example.mysviewpage2

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var initIndicatorDots: LinearLayout
    //    在主线程创建循环器
    private var mHandler = Handler(Looper.getMainLooper())
    private lateinit var viewPage2: ViewPager2
    private val datas = ArrayList<Int>().apply {
        add(R.drawable.tx1)
        add(R.drawable.tx2)
        add(R.drawable.tx3)
        add(R.drawable.tx4)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPage2 = findViewById(R.id.viewpager2)
        val myViewPage2Adapter = MyViewPage2Adapter(datas)
        viewPage2.adapter = myViewPage2Adapter

        // 初始化指示器父布局
        initIndicatorDots = findViewById(R.id.container_indicator)
        viewPage2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            上一个图片索引
            private var lastPosition = 0
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //轮播时，改变指示点
                val current = position % 4
                val last = lastPosition % 4
                initIndicatorDots.getChildAt(current).setBackgroundResource(R.drawable.shape_dot_selected)
                initIndicatorDots.getChildAt(last).setBackgroundResource(R.drawable.shape_dot)
//                将本次图片索引更新到上一个图片索引
                lastPosition = position
            }
        })
        initIndicatorDots()
    }

    //    软件启动时回调
    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(runnable, 500)
    }

    //    退出时移除循环器
    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(runnable)
    }

    private fun initIndicatorDots() {
        Log.d(TAG, "初始化完成")
//        循环数据长度
        for (i in datas.indices) {
//            实例化一个viewImage控件对象
            val imageView = ImageView(this)
//          初始启动，除了第0个，其它都是未选择状态
            if (i == 0) imageView.setBackgroundResource(R.drawable.shape_dot_selected)
            else imageView.setBackgroundResource(R.drawable.shape_dot)
//            为指示器设置宽高
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

//           指示器间距
            layoutParams.marginEnd = 20
            imageView.layoutParams = layoutParams
            initIndicatorDots.addView(imageView)
        }
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