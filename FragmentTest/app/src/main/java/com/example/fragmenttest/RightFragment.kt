package com.example.fragmenttest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RightFragment : Fragment() {
    // 这里为了方便日志打印，我们先定义了一个TAG常量。
    //Kotlin中定义常量都是使用的这种方式，在companion object、单例类或顶层作用域中使用const关键字声明一个变量即可
    companion object {
        const val TAG = "RightFragment"
    }

    // Fragment和Activity关联时
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    // 当Fragment被创建时
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    // Fragment创建视图(布局)时
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.right_fragment, container, false)
    }

    // 与Fragment关联的Activity创建完毕时
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    // Activity由可见变为不可见
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    // Activity位于返回栈的栈顶，并且处于运行状态
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    // 系统准备启动或恢复另一个Activity时
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
    // Activity完全不可见时
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    // Fragment关联的视图被移除时
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    // Activity被销毁前调用
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // Fragment和Activity解除关联时
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}