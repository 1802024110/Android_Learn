package com.example.playaudiotest

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // 媒体播放器的默认构造函数
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化播放器
        initMediaPlayer()

        val play:Button = findViewById(R.id.play)
        val pause:Button = findViewById(R.id.pause)
        val stop:Button = findViewById(R.id.stop)

        play.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }
        pause.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }
        stop.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }
  }

    override fun onDestroy() {
        super.onDestroy()
        // 停止播放
        mediaPlayer.stop()
        // 然后销毁
        mediaPlayer.release()
    }

    private fun initMediaPlayer() {
        // 获得AssetManager实例
        val assetManager = assets
        // 打开文件
        val fd = assetManager.openFd("music.mp3")
        // 设置要使用的数据源
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        // 为同步播放准备播放器
        mediaPlayer.prepare()
    }
}