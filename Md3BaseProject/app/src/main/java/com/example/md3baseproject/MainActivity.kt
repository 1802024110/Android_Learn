package com.example.md3baseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contextView = findViewById<View>(R.id.context_view)
        Snackbar.make(contextView,"这是Snackbar",Snackbar.LENGTH_SHORT).apply {
            setAction("弹出提示"){
                Toast.makeText(context,"这是点击弹出的提示",Toast.LENGTH_SHORT).show()
            }
        }.show()
    }
}