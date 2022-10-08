package com.example.listviewtest

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitAdapter(activity: Activity,val resouceId: Int, data: List<Fruit>) :ArrayAdapter<Fruit>(activity, resouceId, data) {
    // 定义一个内部类来缓存ImageView和TextView控件
    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder:ViewHolder
        val view:View
        if (convertView ==null){
            view = LayoutInflater.from(context).inflate(resouceId,parent,false)
            // 创建两个控件实例
            val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
            val fruitName = view.findViewById<TextView>(R.id.fruitName)
            // 将创建的实例进行缓存。
            viewHolder = ViewHolder(fruitImage,fruitName)
            // 再将缓存好的viewHolder保存再view,tag里面，这样就会存在convertView中，
            view.tag  = viewHolder
        }else{
            view = convertView
            // 存在的话就取出viewHolder
            viewHolder = view.tag as ViewHolder
        }

        val fruit = getItem(position)
        if(fruit!=null){
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }
        // 最后返回布局
        return view
    }
}