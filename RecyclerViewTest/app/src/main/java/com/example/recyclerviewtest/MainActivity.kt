package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>().apply {
        repeat(2) {
            add(Fruit("Apple", R.drawable.apple_pic))
            add(Fruit("Banana", R.drawable.banana_pic))
            add(Fruit("Orange", R.drawable.orange_pic))
            add(Fruit("Watermelon", R.drawable.watermelon_pic))
            add(Fruit("Pear", R.drawable.pear_pic))
            add(Fruit("Grape", R.drawable.grape_pic))
            add(Fruit("Pineapple", R.drawable.pineapple_pic))
            add(Fruit("Strawberry", R.drawable.strawberry_pic))
            add(Fruit("Cherry", R.drawable.cherry_pic))
            add(Fruit("Mango", R.drawable.mango_pic))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        // 设置排列方向是垂直
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }
}