package com.example.viewmodeltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeltest.Repository.getUser
import kotlin.concurrent.thread

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
        val getUserBtn:Button = findViewById(R.id.getUserBtn)
        getUserBtn.setOnClickListener {
            val userId = (0..1000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this,Observer{
            val infoText:TextView = findViewById(R.id.infoText)
            infoText.text = it.firstName
        })

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        val addDataBtn:Button = findViewById(R.id.addDataBtn)
        val updateDataBtn:Button = findViewById(R.id.updateDataBtn)
        val deleteDataBtn:Button = findViewById(R.id.deleteDataBtn)
        val queryDataBtn:Button = findViewById(R.id.queryDataBtn)

        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        deleteDataBtn.setOnClickListener {
            thread{
                userDao.deleteUserByLastName("Hanks")
            }
        }
        queryDataBtn.setOnClickListener{
            thread{
                for (user in userDao.loadAllUsers()){
                    Log.d("MainActivity", user.toString())
                }
            }
        }

    }
    override fun onPause() {
        super.onPause()
        sp.edit{
            putInt("count_reserved",viewModel.counter.value ?: 0)
        }
    }
}