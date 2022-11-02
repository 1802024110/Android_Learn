package com.example.contactstest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    // 联系人列表
    private val contactsList = ArrayList<String>()
    // ListView适配器
    private lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactsView = findViewById<ListView>(R.id.contactsView)
        // 设置适配器布局和数据
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactsList)
        // 加载适配器
        contactsView.adapter = adapter
        // 判断是否有读取联系人的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            // 没有权限则申请权限
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }else{
            readContacts()
        }
    }

    // 请求权限时的事件
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 请求电话权限的标识码是1
        when(requestCode) {
            1 -> {
                // 再次验证权限
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts()
                }else{
                    Toast.makeText(this, "权限获取失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readContacts() {
        // 这里的程序包名和路径使用的是安卓提供的URI
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            // 循环查询结果
            while (moveToNext()){
                val displayName = getString(getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = getString(getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add("$displayName\n$number")
            }
            // 告诉adapter该刷新数据了
            adapter.notifyDataSetChanged()
            // 关闭查询
            close()
        }
    }
}