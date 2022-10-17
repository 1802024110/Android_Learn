package com.example.viewpage2test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(val colors: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>(){

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val container = view.findViewById<RelativeLayout>(R.id.container)
        val titleTv = view.findViewById<TextView>(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var i = position % 4
        holder.titleTv.text = "item$i"
        holder.container.setBackgroundColor(colors.get(i))
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}