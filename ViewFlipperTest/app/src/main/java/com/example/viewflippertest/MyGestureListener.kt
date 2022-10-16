package com.example.viewflippertest

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.ViewFlipper
import java.util.*
import kotlin.concurrent.schedule

// 继承手势检测器
class MyGestureListener(private val myFilper:ViewFlipper,private val context: Context,private val mRadioGroup:RadioGroup) : GestureDetector.SimpleOnGestureListener(){
    private var timeFlat = false
    private val MIN_MOVE = 200;   //最小距离
    //OnFling中根据X轴方向移动的距离和速度来判断当前用户是向左滑还是向右滑，
    // 从而利用showPrevious()或者showNext()来显示上一张或者下一张图片
    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1!=null&&e2!=null){
            if(e1.x-e2.x>MIN_MOVE){
                myFilper.setInAnimation(context,R.anim.right_in)
                myFilper.setOutAnimation(context,R.anim.right_out)
                myFilper.showNext()
            }else if (e2.x - e1.x > MIN_MOVE){
                myFilper.setInAnimation(context,R.anim.left_in)
                myFilper.setOutAnimation(context,R.anim.left_out)
                myFilper.showPrevious()
            }
            val radioButton = mRadioGroup.getChildAt(myFilper.displayedChild) as RadioButton
            radioButton.isChecked = true

//           停止自动轮播
            myFilper.stopFlipping()
//            3秒延时器
            timeFlat = true
            if (timeFlat){
                Timer().schedule(3000){
                    myFilper.stopFlipping()
//                开始自动播放
                    myFilper.startFlipping()
                    timeFlat = false
                }
            }
        }
        return true
    }
}