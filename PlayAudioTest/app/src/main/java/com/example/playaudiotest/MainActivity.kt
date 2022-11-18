package com.example.playaudiotest

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val mediaPlayer = MediaPlayer().apply {
        val fd = assets.openFd("music.mp3")
        setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        prepare()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val play:Button = findViewById(R.id.play)
//        val pause:Button = findViewById(R.id.pause)
//        val stop:Button = findViewById(R.id.stop)
//
//        play.setOnClickListener {
//            if (!mediaPlayer.isPlaying){
//                mediaPlayer.start()
//            }
//        }
//        pause.setOnClickListener {
//            if (mediaPlayer.isPlaying){
//                mediaPlayer.pause()
//            }
//        }
//        stop.setOnClickListener {
//            if (mediaPlayer.isPlaying){
//                mediaPlayer.reset()
//            }
//        }
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.stop()
//        mediaPlayer.release()
//    }
}