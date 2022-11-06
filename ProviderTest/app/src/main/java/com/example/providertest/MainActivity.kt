package com.example.providertest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.contentValuesOf

class MainActivity : AppCompatActivity() {

    private  var bookId : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addData = findViewById<Button>(R.id.addData)
        val deleteData = findViewById<Button>(R.id.deleteData)
        val updateData = findViewById<Button>(R.id.updateData)
        val queryData = findViewById<Button>(R.id.queryData)

        addData.setOnClickListener {
            // 添加数据
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            val values = contentValuesOf("name" to "A Clash of Kings", "author" to "George Martin", "pages" to 1040, "price" to 22.85)
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }

        queryData.setOnClickListener {
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            contentResolver.query(uri,null,null,null,null)?.apply {
                while (moveToNext()){
                    val name = getString(getColumnIndexOrThrow("name"))
                    val author = getString(getColumnIndexOrThrow("author"))
                    val pages = getString(getColumnIndexOrThrow("pages"))
                    val price = getString(getColumnIndexOrThrow("price"))
                    Log.d("MainActivity","book的名字是$name")
                    Log.d("MainActivity","book的作者是$author")
                    Log.d("MainActivity","book的页数是$pages")
                    Log.d("MainActivity","book的价格是$price")
                }
                close()
            }
        }
        updateData.setOnClickListener{
            bookId?.let {
                val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                val values = contentValuesOf("name" to "A Storm of Swords","pages" to 1216, "price" to 24.05)
                contentResolver.update(uri, values,null,null)
            }
        }
        deleteData.setOnClickListener {
            // 删除数据
            Log.d("MainActivity","it是$it")
            bookId?.let {
                val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }
    }
}