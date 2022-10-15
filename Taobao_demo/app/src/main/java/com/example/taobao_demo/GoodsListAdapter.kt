package com.example.taobao_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoodsListAdapter(val goodsList:List<Goods>):RecyclerView.Adapter<GoodsListAdapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val goodsCover:ImageView = view.findViewById(R.id.goods_cover)
        val goodsTitle:TextView = view.findViewById(R.id.goods_title)
        val goodsPrice:TextView = view.findViewById(R.id.goods_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.goods,parent,false)
        return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goods = goodsList[position]
        holder.goodsCover.setImageResource(goods.cover)
        holder.goodsTitle.text = goods.title
        holder.goodsPrice.text = goods.price
    }

    override fun getItemCount(): Int = goodsList.size

}