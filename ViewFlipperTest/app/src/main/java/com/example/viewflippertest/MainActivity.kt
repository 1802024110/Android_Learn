package com.example.viewflippertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ViewFlipper
import java.util.Timer
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var mDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val mRadioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val radioButton = mRadioGroup.getChildAt(0) as RadioButton
        radioButton.isChecked = true

//        实例化viewFlipper
        val mFilper = findViewById<ViewFlipper>(R.id.flipper)
//      实例化手势监听器
        var mMyGestureListener = MyGestureListener(mFilper,applicationContext,mRadioGroup)
//      应用监听器
        mDetector = GestureDetector(this,mMyGestureListener)
//        开始播放
        mFilper.startFlipping()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mDetector.onTouchEvent(event)
    }
}