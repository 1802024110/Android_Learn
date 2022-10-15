package com.example.taobao_demo

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView

class HomeCenterTopScrollListener(val progress:View): RecyclerView.OnScrollListener(){
    private val TAG = "HomeCenterTopScrollListener"
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
//        计算滑动进度的百分比
        val parentPercent = recyclerView.computeHorizontalScrollOffset().toDouble() / (recyclerView.computeHorizontalScrollRange()-recyclerView.computeHorizontalScrollExtent()).toDouble()
//        就算小白条应该是多少边距
        val increaseMargin = 105 * parentPercent
//        获得小白条的布局管理器
        val frameLayout = FrameLayout.LayoutParams(progress.layoutParams)
//        设置布局
        frameLayout.setMargins(increaseMargin.toInt(),0,0,0,)
//        应用边距
        progress.layoutParams = frameLayout
    }
}