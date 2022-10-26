package com.example.broadcastbestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

// 这里要继承BaseActivity
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 登录按钮
        val login = findViewById<Button>(R.id.login)
        // 账号控件
        val accountEdit = findViewById<EditText>(R.id.accountEdit)
        // 密码控件
        val passwordEdit = findViewById<EditText>(R.id.passwordEdit)
        // 监听点击登录按钮
        login.setOnClickListener {
            // 获得账号
            val account = accountEdit.text.toString()
            // 获得密码
            val password = passwordEdit.text.toString()
            if (account == "admin" && password == "123456"){
                // 如果相等就创建Intent
                val intent = Intent(this,MainActivity::class.java)
                // 启动主视图
                startActivity(intent)
                // 关闭这个视图
                finish()
            }else{
                // 提示错误
                Toast.makeText(this,"账号或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}