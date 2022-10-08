package com.example.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val data = listOf("Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango")
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
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        val listView  = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        // 关键代码开始
        // 监听item点检事件，position判断用户点击的是哪一个子项。
        // 在Kotlin中未被使用的参数可以使用_替代
        listView.setOnItemClickListener{ _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }
        // 关键代码结束
    }
}