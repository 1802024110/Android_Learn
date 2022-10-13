package com.example.taobao_demo

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeCenterTopAdapter(val homeCenterTopList:List<HomeCenterTop>):RecyclerView.Adapter<HomeCenterTopAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val homeCenterTopIcon:ImageButton = view.findViewById(R.id.home_center_top_icon)
        val homeCenterTopText:TextView = view.findViewById(R.id.home_center_top_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = homeCenterTopList.size
}