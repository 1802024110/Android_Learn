package com.example.databasetest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val createDatabase = findViewById<Button>(R.id.createDatabase)
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        createDatabase.setOnClickListener{
            dbHelper.writableDatabase
        }
        val addData = findViewById<Button>(R.id.addData)
        addData.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "第一行代码3")
                put("author", "郭霖")
                put("pages", 810)
                put("price",9.9)
            }
            db?.insert("Book",null,values1)
            val values2 = ContentValues().apply {
                put("name","第二行代码")
                put("author","梦无念")
                put("pages",233)
                put("price",10)
            }
            db?.insert("Book",null,values2)
        }
        val updateData = findViewById<Button>(R.id.updateData)
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("price", "100")
            }
            db?.update("Book",values,"name = ?",arrayOf("第二行代码"))
        }
        val deleteData = findViewById<Button>(R.id.deleteData)
        deleteData.setOnClickListener{
            val db = dbHelper.writableDatabase
            db?.delete("Book","pages > ?",arrayOf("800"))
        }
        val queryData = findViewById<Button>(R.id.queryData)
        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if (cursor.moveToFirst()){
                do{
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    val author = cursor.getString(cursor.getColumnIndexOrThrow("author"))
                    val pages = cursor.getString(cursor.getColumnIndexOrThrow("pages"))
                    val price = cursor.getString(cursor.getColumnIndexOrThrow("price"))
                    Log.d("MainActivity","book name is $name")
                    Log.d("MainActivity","book name is $author")
                    Log.d("MainActivity","book name is $pages")
                    Log.d("MainActivity","book name is $price")
                }while (cursor.moveToNext())
            }
            cursor.close()
        }

        val replaceData = findViewById<Button>(R.id.replaceData)
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 开启事务
            db.beginTransaction()
            try {
                db.delete("Book",null,null)
                // 手动模拟一个异常，让事务失败
                if (false) throw java.lang.NullPointerException()
                val values = ContentValues().apply {
                    put("name","权力的游戏")
                    put("author","乔治。马丁")
                    put("pages",720)
                    put("price",20.85)
                }
                db.insert("Book",null,values)
                // 设置事务执行成功
                db.setTransactionSuccessful()
            }catch (e: Exception) {
                e.printStackTrace()
            }finally {
                // 结束事务
                db.endTransaction()
            }
        }
    }
}