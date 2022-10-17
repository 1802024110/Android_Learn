package com.example.viewpage2test

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private var mHandler = Handler(Looper.getMainLooper())
    private var lastPosition = 0
    private var colors  = ArrayList<Int>().apply {
        add(Color.BLUE)
        add(Color.YELLOW)
        add(Color.GREEN)
        add(Color.RED)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager2 = findViewById(R.id.viewpager2)
        indicatorContainer = findViewById(R.id.container_indicator)

        initIndicatorDots()

        val viewPagerAdapter = ViewPagerAdapter(colors)
        viewPager2.adapter = viewPagerAdapter
        viewPager2.setCurrentItem(500)
        lastPosition = 500

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //轮播时，改变指示点
                val current = position % 4
                val last = lastPosition % 4
                indicatorContainer.getChildAt(current)
                    .setBackgroundResource(R.drawable.shape_dot_selected)
                indicatorContainer.getChildAt(last).setBackgroundResource(R.drawable.shape_dot)
                lastPosition = position
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(runnable,500)
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(runnable)
    }
    private fun initIndicatorDots() {
        for (i in colors.indices) {
            val imageView = ImageView(this)
            if (i == 0) imageView.setBackgroundResource(R.drawable.shape_dot_selected) else imageView.setBackgroundResource(
                R.drawable.shape_dot
            )
            //为指示点添加间距
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.marginEnd = 4
            imageView.setLayoutParams(layoutParams)
            //将指示点添加进容器
            indicatorContainer.addView(imageView)
        }
    }
    suspend fun test(){

    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            //获得轮播图当前的位置
            var currentPosition = viewPager2.currentItem
            currentPosition++
            viewPager2.setCurrentItem(currentPosition, true)
            mHandler.postDelayed(this, 5000)
        }
    }
}