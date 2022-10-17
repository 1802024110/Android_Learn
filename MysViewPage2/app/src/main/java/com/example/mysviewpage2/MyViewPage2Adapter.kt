package com.example.mysviewpage2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MyViewPage2Adapter(private val imageList: ArrayList<Int>) : RecyclerView.Adapter<MyViewPage2Adapter.ViewHolder>(){
    inner class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        通过取余，一直循环
        val i  = position % imageList.size
        holder.imageView.setImageResource(imageList[i])
    }

//    设置一个超级大的数(2147483647)，达成理论无限滚动
    override fun getItemCount(): Int = Int.MAX_VALUE
}