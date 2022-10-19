package com.example.taobao_demo

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.taobao_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private val list1 = ArrayList<HomeCenterTop>().apply {
        add(HomeCenterTop(R.drawable.img_11,"天猫新品1",R.drawable.img_26,"充值中心"))
        add(HomeCenterTop(R.drawable.img_13,"今日爆款",R.drawable.img_29,"淘鲜达"))
        add(HomeCenterTop(R.drawable.img_15,"饿了么",R.drawable.img_31,"领淘金币"))
        add(HomeCenterTop(R.drawable.img_17,"芭芭农村",R.drawable.img_33,"阿里拍卖"))
        add(HomeCenterTop(R.drawable.img_19,"天猫超市",R.drawable.img_36,"分类"))
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
    private val list3 = ArrayList<Int>().apply {
        add(R.mipmap.lbt1)
        add(R.mipmap.lbt2)
        add(R.mipmap.lbt3)
        add(R.mipmap.lbt4)
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

        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)
        val indicator = findViewById<LinearLayout>(R.id.indicator)
        val viewPager2Adapter = ViewPage2Adapter(list3,indicator)
        viewPager2.adapter = viewPager2Adapter
    }


}