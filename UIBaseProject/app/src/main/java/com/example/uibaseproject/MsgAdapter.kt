package com.example.uibaseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(val msgList:List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
//    inner class LeftViewHolder(view: View,val leftMsg : TextView=view.findViewById(R.id.leftMsg)) : RecyclerView.ViewHolder(view)
    inner class LeftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
    }
    inner class RightViewHolder(view: View,val rightMsg : TextView=view.findViewById(R.id.rightMsg)) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int = msgList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  = when(viewType){
        Msg.TYPE_RECEIVED -> LeftViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false))
        else -> RightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            LeftViewHolder -> holder
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}