package com.example.viewmodeltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    // 预定义ViewModel
    lateinit var viewModel:MainViewModel
    // 访问和修改Context.getSharedPreferences返回的首选项数据的接口。
    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 实例化一个SharedPreferences对象，以访问此活动私有的文件。
        sp = getPreferences(Context.MODE_PRIVATE)
        // 获取上一次存入的计数器
        val countReserved = sp.getInt("count_reserved", 0)
        // 将获取到的计算器存入viewModel
        viewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        val plusOneBth:Button = findViewById(R.id.plusOneBtn)
        plusOneBth.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }

        val clearBtn:Button = findViewById(R.id.clearBtn)
        clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }
    private fun refreshCounter() {
        val infoText:TextView = findViewById(R.id.infoText)
        infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("count_reserved", viewModel.counter)
        }
    }
}