package com.example.broadcastbestpractice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 关键代码开始
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        val account = prefs.getString("account", "")
        val password = prefs.getString("password", "")
        val accountEdit:EditText = findViewById(R.id.accountEdit)
        val passwordEdit:EditText = findViewById(R.id.passwordEdit)
        val rememberPass:CheckBox = findViewById(R.id.rememberPass)
        if(isRemember) {
            // 将账号和密码都设置到文本框中
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPass.isChecked = true
        }
        // 关键代码结束

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            // 关键代码开始
            if (account == "admin" && password == "123456"){
                val editor = prefs.edit()
                // 检查复选框是否被选中
                if(rememberPass.isChecked){
                    editor.putBoolean("remember_password",true)
                    editor.putString("account",account)
                    editor.putString("password",password)
                }else{
                    editor.clear()
                }
                editor.apply()
                // 关键代码结束

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"账号或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}