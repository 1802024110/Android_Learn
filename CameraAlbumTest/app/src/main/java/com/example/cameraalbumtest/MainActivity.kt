package com.example.cameraalbumtest

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
                var imageView: ImageView = findViewById(R.id.imageView)
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                imageView.setImageBitmap(bitmap)
    }

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

        val fromAlbumBtn: Button = findViewById(R.id.fromAlbumBtn)
        fromAlbumBtn.setOnClickListener {
            selectImage.launch("image/*")
        }
    }
}