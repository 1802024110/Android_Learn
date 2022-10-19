package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment : Fragment() {
    private lateinit var news_content_frag:RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // 加载news_content_frag布局
        news_content_frag = inflater.inflate(R.layout.news_content_frag, container, false) as RelativeLayout
        return news_content_frag
    }
    // 该方法用于替换控件内容
    fun refresh(title: String, content: String) {
        //            设置布局可见

        news_content_frag.findViewById<LinearLayout>(R.id.contentLayout).visibility = View.VISIBLE
        // 更新标题
        news_content_frag.findViewById<TextView>(R.id.newsTitle).text = title // 刷新新闻的标题
        // 更新内容
        news_content_frag.findViewById<TextView>(R.id.newsContent).text = content // 刷新新闻的内容
    }
}
