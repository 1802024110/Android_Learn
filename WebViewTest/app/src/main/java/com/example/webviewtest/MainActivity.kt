package com.example.webviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView:WebView = findViewById(R.id.webView)
        webView.apply {
            // 告诉WebView启用JavaScript执行。默认值为false。
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://007666.xyz/")
        }
    }
}