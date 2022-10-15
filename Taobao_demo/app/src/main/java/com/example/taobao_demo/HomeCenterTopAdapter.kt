package com.example.taobao_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeCenterTopAdapter(val homeCenterTopList:List<HomeCenterTop>):RecyclerView.Adapter<HomeCenterTopAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val homeCenterTopIcon1:ImageButton = view.findViewById(R.id.home_center_top_icon1)
        val homeCenterTopText1:TextView = view.findViewById(R.id.home_center_top_text1)
        val homeCenterTopIcon2:ImageButton = view.findViewById(R.id.home_center_top_icon2)
        val homeCenterTopText2:TextView = view.findViewById(R.id.home_center_top_text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_center_top, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeCenterTop = homeCenterTopList[position]
        holder.homeCenterTopIcon1.setImageResource(homeCenterTop.resId1)
        holder.homeCenterTopText1.text = homeCenterTop.title1
        holder.homeCenterTopIcon2.setImageResource(homeCenterTop.resId2)
        holder.homeCenterTopText2.text = homeCenterTop.title2
    }

    override fun getItemCount(): Int = homeCenterTopList.size
}