package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>().apply {
        repeat(2) {
            add(Fruit(getRandomLengthString(getRandomLengthString("Apple")), R.drawable.apple_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Banana")), R.drawable.banana_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Orange")), R.drawable.orange_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Watermelon")), R.drawable.watermelon_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Pear")), R.drawable.pear_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Grape")), R.drawable.grape_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Pineapple")), R.drawable.pineapple_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Strawberry")), R.drawable.strawberry_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Cherry")), R.drawable.cherry_pic))
            add(Fruit(getRandomLengthString(getRandomLengthString("Mango")), R.drawable.mango_pic))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      更改为网格布局
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

//  将水果名字随机重复。
    private fun getRandomLengthString(str:String):String{
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n){
            builder.append(str)
        }
        return builder.toString()
    }
}