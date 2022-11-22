package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendRequestBtn: Button = findViewById(R.id.sendRequestBtn)
        sendRequestBtn.setOnClickListener {
            sendRequestWithHttpUrl("https://jsdelivr.nodream.cf/gh/1802024110/GitHub_Oss@main/file/安卓第一行代码解析xml数据.xml")
        }
    }

    private fun sendRequestWithHttpUrl(url : String){
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().apply {
                    url(url)
                }.build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                responseData?.let {
                    // 将输出方法改为转换xml
                    parseXMLWithPull(it)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            // 创建可用于创建XML拉式解析器的PullParserFactory的新实例。
            val factory = XmlPullParserFactory.newInstance()
            // 使用当前配置的工厂特性创建XML Pull Parser的新实例。
            val xmlPullParser = factory.newPullParser()
            // 将解析器的输入源设置为给定的字符串读取器并重置解析器。
            xmlPullParser.setInput(StringReader(xmlData))
            // 返回当前事件的类型(START_TAG, END_TAG, TEXT等)。
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            // xml文档的逻辑结束
            while (eventType!=XmlPullParser.END_DOCUMENT){
                // 对于START_TAG或END_TAG事件，在启用名称空间时返回当前元素的(本地)名称。
                val nodeName = xmlPullParser.name
                when (eventType) {
                    // 读取开始标记时。
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }
                    // 完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if ("app" == nodeName) {
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                    }
                }
                // 获取下一个解析事件
                eventType = xmlPullParser.next()
            }
        } catch (e:Exception){}
    }
}