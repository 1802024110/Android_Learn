package com.example.taobao_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taobao_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private val list = ArrayList<HomeCenterTop>().apply {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val layoutManager = LinearLayoutManager(this)
        val home_recyclerView1 = findViewById<RecyclerView>(R.id.home_recyclerView1)
        home_recyclerView1.layoutManager = layoutManager
        val adapter = HomeCenterTopAdapter()
    }
}