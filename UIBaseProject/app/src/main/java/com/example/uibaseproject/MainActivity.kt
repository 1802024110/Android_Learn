package com.example.uibaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //
    private val msgList = ArrayList<Msg>().apply {
        add( Msg("Hello",Msg.TYPE_RECEIVED))
        add( Msg("HI",Msg.TYPE_SENT))
        add( Msg("小明",Msg.TYPE_RECEIVED))
        add( Msg("小红",Msg.TYPE_SENT))
        add( Msg("在做什么",Msg.TYPE_RECEIVED))
        add( Msg("写代码",Msg.TYPE_SENT))
    }
    private var adapter:MsgAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager  = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter

        val send = findViewById<Button>(R.id.send)
        send.setOnClickListener{
            val inputText = findViewById<EditText>(R.id.inputText)
            val content = inputText.text.toString()
            if(content.isNotEmpty()){
                val msg = Msg(content,Msg.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size-1)
                recyclerView.scrollToPosition(msgList.size -1)
                inputText.setText("")
            }
        }
    }
}