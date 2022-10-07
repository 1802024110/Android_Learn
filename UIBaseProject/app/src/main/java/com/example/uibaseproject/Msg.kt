package com.example.uibaseproject

class Msg (val context:String,val type:Int){
    companion object{
        const val TYPE_RECEIVED=0
        const val TYPE_SENT=1
    }
}