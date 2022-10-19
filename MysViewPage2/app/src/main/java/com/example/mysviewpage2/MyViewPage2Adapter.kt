package com.example.mysviewpage2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MyViewPage2Adapter(private val imageList: ArrayList<Int>) : RecyclerView.Adapter<MyViewPage2Adapter.ViewHolder>(){
    private val TAG = "MyViewPage2Adapter"
    inner class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val i  = position % imageList.size
        holder.imageView.setImageResource(imageList[i])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}