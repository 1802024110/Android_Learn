package com.example.taobao_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.taobao_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var mainBinding:ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val barBinding = mainBinding.bar
        setContentView(mainBinding.root)
        barBinding.homeButton.setOnClickListener(this)
        barBinding.weitaoButton.setOnClickListener(this)
        barBinding.xiaoxiButton.setOnClickListener(this)
        barBinding.gwucheButton.setOnClickListener(this)
        barBinding.wodeButton.setOnClickListener(this)
    }

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
    fun backSrc(v: View?){
        val barBinding =  mainBinding.bar
        when(v?.id){
            R.id.home_button->{
                barBinding.weitaoButton.setImageResource(R.drawable.weitao)
                barBinding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                barBinding.gwucheButton.setImageResource(R.drawable.gwuche)
                barBinding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.weitao_button->{
                barBinding.homeButton.setImageResource(R.drawable.home__1_)
                barBinding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                barBinding.gwucheButton.setImageResource(R.drawable.gwuche)
                barBinding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.xiaoxi_button->{
                barBinding.homeButton.setImageResource(R.drawable.home__1_)
                barBinding.weitaoButton.setImageResource(R.drawable.weitao)
                barBinding.gwucheButton.setImageResource(R.drawable.gwuche)
                barBinding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.gwuche_button->{
                barBinding.homeButton.setImageResource(R.drawable.home__1_)
                barBinding.weitaoButton.setImageResource(R.drawable.weitao)
                barBinding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                barBinding.wodeButton.setImageResource(R.drawable.wode)
            }
            R.id.wode_button->{
                barBinding.homeButton.setImageResource(R.drawable.home__1_)
                barBinding.weitaoButton.setImageResource(R.drawable.weitao)
                barBinding.xiaoxiButton.setImageResource(R.drawable.xiaoxi)
                barBinding.gwucheButton.setImageResource(R.drawable.gwuche)
            }
        }
    }
}