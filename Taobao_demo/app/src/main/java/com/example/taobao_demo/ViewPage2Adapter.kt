package com.example.taobao_demo

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPage2Adapter(private val imageList:ArrayList<Int>,private val indicatorLinearLayout: LinearLayout): RecyclerView.Adapter<ViewPage2Adapter.ViewHolder>() {
    private val TAG = "ViewPage2Adapter"
    private var mHandler = Handler(Looper.getMainLooper())
    private lateinit var viewPager2:ViewPager2
    inner class ViewHolder(val imageView: ImageView):RecyclerView.ViewHolder(imageView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = ImageView(parent.context)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = layoutParams
        return ViewHolder(imageView)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewPager2 = recyclerView.parent as ViewPager2
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //            上一个图片索引
            private var lastPosition = 1
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //轮播时，改变指示点
                val current = position % 4
                val last = lastPosition % 4
                indicatorLinearLayout.getChildAt(current).setBackgroundResource(R.drawable.shape_dot_selected)
                indicatorLinearLayout.getChildAt(last).setBackgroundResource(R.drawable.shape_dot)
//                将本次图片索引更新到上一个图片索引
                lastPosition = position
            }
        })
        for (i in imageList.indices) {
            val imageView = ImageView(recyclerView.context)
            Log.d(TAG, "i is ${i}")
            if (i == 0) imageView.setBackgroundResource(R.drawable.shape_dot_selected)
            else imageView.setBackgroundResource(R.drawable.shape_dot)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.marginEnd = 20
            imageView.layoutParams = layoutParams
            indicatorLinearLayout.addView(imageView)
        }
        mHandler.postDelayed(runnable, 3000)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mHandler.removeCallbacks(runnable)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val i  = position % imageList.size
        holder.imageView.setImageResource(imageList[i])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    private val runnable = object : Runnable {
        override fun run() {
            //获得轮播图当前的位置
            var currentPosition = viewPager2.currentItem
            currentPosition++
            viewPager2.setCurrentItem(currentPosition, true)
            mHandler.postDelayed(this, 3000)
        }
    }
}