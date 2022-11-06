package com.example.databasetest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class DatabaseProvider : ContentProvider() {

    // 访问Book表中所有的数据
    private val bookDir = 0
    // 访问单条
    private val bookItem = 1
    // 访问Category表中的所有数据
    private val categoryDir = 2
    // 访问Category中的单条数据
    private val categoryItem = 3
    // 定义Uri的authority
    private val authority = "com.example.databasetest.provider"
    // 预定义
    private lateinit var dbHelper : MyDatabaseHelper
    // 对UriMatcher进行初始化操作
    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"book",bookDir)
        matcher.addURI(authority,"book/#",bookItem)
        matcher.addURI(authority,"category",categoryDir)
        matcher.addURI(authority,"category/#",categoryItem)
        matcher
    }

    //  同样的获得SQLiteDatabase对象
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {
        val db = it.writableDatabase
        val deleteRows = when (uriMatcher.match(uri)) {
            bookDir -> db.delete("Book",selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                // 这里是返回一个受影响的int行数
                db.delete("Book","id = ?", arrayOf(bookId))
            }
            categoryDir -> db.delete("Category",selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category","id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deleteRows
    }?: 0

    // 这里按照上面讲的编写规则来就好
    override fun getType(uri: Uri) = when (uriMatcher.match(uri)){
        bookDir -> "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book"
        bookItem -> "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book"
        categoryDir -> "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category"
        categoryItem ->  "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category"
        else -> null
    }

    //  同样的获得SQLiteDatabase对象
    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let{
        val db = it.writableDatabase
        val uriReturn = when(uriMatcher.match(uri)) {
            bookDir,bookItem ->{
                val newBookId = db.insert("Book",null,values)
                // 因为insert()方法需要返回一个新增数据的URI，所以需要转换一下
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir,categoryItem -> {
                val newCategoryId = db.insert("Category",null,values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }
    // 先看看上下文(context)有没有东西，如果没有东西就返回false，表示ContentProvider初始化失败。
    override fun onCreate() = context?.let {
        // 实例化SQLiteOpenHelper
        dbHelper = MyDatabaseHelper(it,"BookStore.db",2)
        // 直接返回true表示初始化成功
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        // 获得SQLiteDatabase对象
        val db = it.readableDatabase
        // 创建一个查询结果cursor变量，根据匹配uri来判断访问的那张表
        val cursor = when (uriMatcher.match(uri)){
            // 访问所有数据，直接调用SQLiteDatabase的query()方法查询，返回一个Cursor对象
            bookDir -> db.query("Book", projection, selection, selectionArgs,null,null,sortOrder)
            // 访问单条数据
            bookItem -> {
                // 调用requestPermissions()方法，URI权限后的部分以“/”分割，且保存到一个字符串列表
                // 这个列表的第0个位置存放的就是路径，第1个位置存放的就是id了
                val bookId = uri.pathSegments[1]
                // 接着再查询表，表名    传入的查询列   约束条件           取出的查询Id                              排序
                db.query("Book",projection,"id = ?", arrayOf(bookId),null,null,sortOrder)
            }
            categoryDir -> db.query("Category",projection,selection,selectionArgs,null,null,sortOrder)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category",projection,"id = ?", arrayOf(categoryId),null,null,sortOrder)
            }
            else -> null
        }
        // 返回一个Cursor
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book",values,selection,selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                // update需要返回受影响的行数
                db.update("Book",values,"id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("Category",values,selection,selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category",values,"id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updateRows
    } ?: 0
}