package com.example.cameraalbumtest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    private val getImage = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it){
                imageUri?.let {
                    var imageView: ImageView = findViewById(R.id.imageView)
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                    imageView.setImageBitmap(bitmap)
                }
            }
    }

    // 这里新注册了一个启动器用于选择照片
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
                var imageView: ImageView = findViewById(R.id.imageView)
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                imageView.setImageBitmap(bitmap)
    }
    // 结束

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val takePhotoBtn: Button = findViewById(R.id.takePhotoBtn)
        takePhotoBtn.setOnClickListener {
            outputImage = File(externalCacheDir,"output_image.jpg")
            imageUri = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"com.exa" +
                        "mple.cameraalbumtest.fileprovider",outputImage)
            }else{
                Uri.fromFile(outputImage)
            }
            getImage.launch(imageUri)
        }

        // 这里绑定按钮
        val fromAlbumBtn: Button = findViewById(R.id.fromAlbumBtn)
        fromAlbumBtn.setOnClickListener {
            // 启动启动器，传入类型
            selectImage.launch("image/*")
        }
        // 结束
    }
}