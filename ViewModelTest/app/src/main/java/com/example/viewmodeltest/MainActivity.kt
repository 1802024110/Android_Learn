package com.example.viewmodeltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeltest.Repository.getUser

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:MainViewModel
    lateinit var sp:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        val plusOneBth:Button = findViewById(R.id.plusOneBtn)
        plusOneBth.setOnClickListener {
            viewModel.plusOne()
        }
        val clearBtn:Button = findViewById(R.id.clearBtn)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this,Observer{
            val infoText:TextView = findViewById(R.id.infoText)
            infoText.text = it.toString()
        })

        //
        val getUserBtn:Button = findViewById(R.id.getUserBtn)
        getUserBtn.setOnClickListener {
            val userId = (0..1000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this,Observer{
            val infoText:TextView = findViewById(R.id.infoText)
            infoText.text = it.firstName
        })
        //
    }
    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("count_reserved",viewModel.counter.value ?: 0)
        }
    }
}