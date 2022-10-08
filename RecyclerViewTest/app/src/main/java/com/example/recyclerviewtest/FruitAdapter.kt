package com.example.recyclerviewtest

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// FruitAdapter的参数是传入数据源。
class FruitAdapter(val fruitList:List<Fruit>):RecyclerView.Adapter<FruitAdapter.ViewHolder>(){
    // View参数通常是RecyclerView子项的最外层布局，这样就能在内部使用findViewById()找到ImageView和TextView.
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val fruitImage : ImageView = view.findViewById(R.id.fruitImage)
        val fruitName : TextView = view.findViewById(R.id.fruitName)
    }

    // 该方法用于将布局传入构造函数最后将加载好控件的ViewHolder实例返回
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.fruit_item,parent,false)
        return ViewHolder(view)
    }

    // 每个子项被滚到屏幕内时执行，通过position获得当前子项index.然后设置image和name
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    // 该方法用于获取数据源长度
    override fun getItemCount(): Int = fruitList.size
}