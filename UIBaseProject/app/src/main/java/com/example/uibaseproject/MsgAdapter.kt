package com.example.uibaseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MsgAdapter(val msgList:List<Msg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
//    左边控件内部类，用于缓存控件
    inner class LeftViewHolder(view: View,val leftMsg : TextView=view.findViewById(R.id.leftMsg)) : RecyclerView.ViewHolder(view)
//    右边控件内部类，用于缓存控件
    inner class RightViewHolder(view: View,val rightMsg : TextView=view.findViewById(R.id.rightMsg)) : RecyclerView.ViewHolder(view)
//    获得是接受还是发送
    override fun getItemViewType(position: Int): Int = msgList[position].type
// 根据不同类型来加载不同布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  = when(viewType){
        Msg.TYPE_RECEIVED -> LeftViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item,parent,false))
        else -> RightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.context
            is RightViewHolder -> holder.rightMsg.text = msg.context
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = msgList.size
}