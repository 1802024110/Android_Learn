package com.example.taobao_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.taobao_demo.databinding.ActivityMainBinding
import com.example.taobao_demo.databinding.NavBarBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val barBinding = mainBinding.bar
        setContentView(mainBinding.root)
        barBinding.homeButton.setOnClickListener(this)
        barBinding.weitaoButton.setOnClickListener(this)
        barBinding.xiaoxiButton.setOnClickListener(this)
        barBinding.gwucheButton.setOnClickListener(this)
        barBinding.wodeButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.i(TAG,"你点了$v")
    }
}