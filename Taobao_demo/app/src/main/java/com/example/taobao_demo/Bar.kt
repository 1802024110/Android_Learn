package com.example.taobao_demo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.taobao_demo.databinding.NavBarBinding

class Bar(context:Context,attrs:AttributeSet):LinearLayout(context,attrs), View.OnClickListener {
//    获得底部导航的View
    private val root = View.inflate(context,R.layout.nav_bar,this)
//    加载底部的Binding
    private val binding = NavBarBinding.bind(root)
//    布局文件读到这里的时候加载布局文件
    init {
//    加载布局
        LayoutInflater.from(context).inflate(R.layout.nav_bar,this)
//     换图标
        binding.homeButton.setOnClickListener(this)
       binding.weitaoButton.setOnClickListener(this)
       binding.xiaoxiButton.setOnClickListener(this)
       binding.gwucheButton.setOnClickListener(this)
       binding.wodeButton.setOnClickListener(this)
    }

// 该方法用于替换图标
    override fun onClick(v: View?) {
                backSrc(v)
        when(v?.id){
            R.id.home_button->{ v as ImageButton
                v.setImageResource(R.drawable.home_click)
            }
            R.id.weitao_button->{ v as ImageButton
                v.setImageResource(R.drawable.weitao_click)
            }
            R.id.xiaoxi_button->{ v as ImageButton
                v.setImageResource(R.drawable.xiaoxi_click)
            }
            R.id.gwuche_button->{ v as ImageButton
                v.setImageResource(R.drawable.gowuche_click)
            }
            R.id.wode_button->{ v as ImageButton
                v.setImageResource(R.drawable.wode_click)
            }
        }
    }
//    改方法用于将没变的图标恢复
    fun backSrc(v: View?){
        when(v?.id){
            R.id.home_button->{
                binding.weitaoButton.setImageResource(R.drawable.weitao)
                binding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                binding.gwucheButton.setImageResource(R.drawable.gwuche)
                binding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.weitao_button->{
                binding.homeButton.setImageResource(R.drawable.home__1_)
                binding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                binding.gwucheButton.setImageResource(R.drawable.gwuche)
                binding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.xiaoxi_button->{
                binding.homeButton.setImageResource(R.drawable.home__1_)
                binding.weitaoButton.setImageResource(R.drawable.weitao)
                binding.gwucheButton.setImageResource(R.drawable.gwuche)
                binding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.gwuche_button->{
                binding.homeButton.setImageResource(R.drawable.home__1_)
                binding.weitaoButton.setImageResource(R.drawable.weitao)
                binding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                binding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.wode_button->{
                binding.homeButton.setImageResource(R.drawable.home__1_)
                binding.weitaoButton.setImageResource(R.drawable.weitao)
                binding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                binding.gwucheButton.setImageResource(R.drawable.gwuche)
            }
        }
    }
}