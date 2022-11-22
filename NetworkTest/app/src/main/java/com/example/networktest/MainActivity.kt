package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import java.io.StringReader
import kotlin.concurrent.thread
import java.lang.StringBuilder
import javax.xml.parsers.SAXParserFactory

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
                    try {
                        val factory = SAXParserFactory.newInstance()
                        val xmlReader = factory.newSAXParser().XMLReader
                        val handler = ContentHandler()
                        // 将ContentHandler的实例设置到XMLReader中
                        xmlReader.contentHandler = handler
                        // 开始执行解析
                        xmlReader.parse(InputSource(StringReader(xmlData)))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    inner class ContentHandler : DefaultHandler() {
        private var nodeName = ""
        private lateinit var id:StringBuilder
        private lateinit var name: StringBuilder
        private lateinit var version:StringBuilder

        // 开始XML解析的时候
        override fun startDocument() {
            // 将这几个变量重置
            id = StringBuilder()
            name = StringBuilder()
            version = StringBuilder()
        }

        // 解析某个节点的时候
        override fun startElement(
            uri: String, localName: String, qName: String, attributes: Attributes
        ) {
            // 当前节点名字
            nodeName = localName
            Log.d("ContentHandler", "uri is $uri")
            Log.d("ContentHandler", "localName is $localName")
            Log.d("ContentHandler", "qName is $qName")
            Log.d("ContentHandler", "attributes is $attributes")
        }

        //获取节点/中内容的时候
        override fun characters(ch: CharArray, start: Int, length: Int) {
            // 根据当前节点名判断将内容添加到哪一个StringBuilder对象中
            when(nodeName){
                "id" -> id.append(ch, start, length)
                "name" -> name.append(ch, start, length)
                "version" -> version.append(ch, start, length)
            }
        }

        // 完成解析某个节点的时候
        override fun endElement(uri: String, localName: String, qName: String) {
            // 判断app节点是否解析完成
            if ("app" == localName) {
                Log.d("ContentHandler", "id is ${id.toString().trim()}")
                Log.d("ContentHandler", "name is ${name.toString().trim()}")
                Log.d("ContentHandler", "version is ${version.toString().trim()}")
                // 最后要将StringBuilder清空
                id.setLength(0)
                name.setLength(0)
                version.setLength(0)
            }
        }

        // 完成整个XML解析的时候
        override fun endDocument() {}
    }
}