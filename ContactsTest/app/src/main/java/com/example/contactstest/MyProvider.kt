package com.example.contactstest

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class MyProvider : ContentProvider() {
    // 初始化ContentProvider时调用的，通常用于数据库的创建和升级操作，返回true表示ContentProvider初始化成功，false失败。
    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    // 从ContentProvider中查询数据，查询结果放置到Cursor(游标)对象返回。
    override fun query(
        // 确定查询的表
        uri: Uri,
        // 确定查询的列
        projection: Array<out String>?,
        // 约束条件
        selection: String?,
        // 约束条件的具体值
        selectionArgs: Array<out String>?,
        // 排序
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    // 根据传入的URI返回相应的MIME类型
    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    // 向ContentProvider中添加一条数据，返回添加的新记录的URI
    override fun insert(
        // 要添加的表
        uri: Uri,
        // 待添加的数据
        values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    // 从ContentProvider中删除数据，返回被删除的行数
    override fun delete(
        // 确定被删除的表名
        uri: Uri,
        // 约束条件
        selection: String?,
        // 约束具体值
        selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    // 更新ContentProvider中的数据，返回受影响行数
    override fun update(
        // 确定添加的表名
        uri: Uri,
        // 待添加的数据
        values: ContentValues?,
        // 约束条件
        selection: String?,
        // 约束条件的具体值
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}