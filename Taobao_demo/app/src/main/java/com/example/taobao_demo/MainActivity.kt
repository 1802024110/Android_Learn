package com.example.taobao_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taobao_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private val list1 = ArrayList<HomeCenterTop>().apply {
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
        add(HomeCenterTop(R.drawable.tianmao,"天猫新品1",R.drawable.tianmao,"天猫新品2"))
    }
    private val list2 = ArrayList<Goods>().apply {
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
        add(Goods(R.drawable.gwuche,"3060","￥2302"))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
//        获得Main布局文件的布局管理器
        val layoutManager = LinearLayoutManager(this)
//        设置布局方式为垂直
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        找到中间上半部分的列表控件
        val home_recyclerView1 = findViewById<RecyclerView>(R.id.home_recyclerView1)
//        加载布局管理器
        home_recyclerView1.layoutManager = layoutManager
//        实例化适配器
        val adapter = HomeCenterTopAdapter(list1)
//        加载适配器
        home_recyclerView1.adapter = adapter
//        实例化滚动监听事件，传入需要被改变的控件
        val homeCenterTopScrollListener = HomeCenterTopScrollListener(findViewById(R.id.home_center_top_progress))
//        设置上半部分的滚动事件，改变进度条
        home_recyclerView1.addOnScrollListener(homeCenterTopScrollListener)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val goodsRecyclerView = findViewById<RecyclerView>(R.id.goods_list)
        goodsRecyclerView.layoutManager = staggeredGridLayoutManager
        val goods_adapter  =GoodsListAdapter(list2)
        goodsRecyclerView.adapter = goods_adapter
    }


}