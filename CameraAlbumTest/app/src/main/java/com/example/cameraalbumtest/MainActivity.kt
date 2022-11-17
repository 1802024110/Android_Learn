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
    val takePhoto = 1
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    /* registerForActivityResult()注册一个请求来启动一个活动的ActivityResultLauncher,接收两个参数
    * 参数一: 约定的intent合约，需要查表
    * 参数二: 执行的回调方法
    * */
    private val getImage = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        // ActivityResultContracts.TakePicture()是拍照的合约,回调方法传入一个指示成功的布尔值接收一个Uri
            // 判断拍照成功没有
            if (it){
                // 判断有没有uri链接
                imageUri?.let {
                    var imageView: ImageView = findViewById(R.id.imageView)
                    // 打开图片然后转为位图
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                    // 设置图片
                    imageView.setImageBitmap(bitmap)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val takePhotoBtn: Button = findViewById(R.id.takePhotoBtn)

        takePhotoBtn.setOnClickListener {
            // 创建File对象用于存储拍的照片,放在当前应用缓存数据的位置
            outputImage = File(externalCacheDir,"output_image.jpg")
            // 检查系统是否低于Android7
            imageUri = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                // 低于就将File转为Uri对象
                FileProvider.getUriForFile(this,"com.exa" +
                        "mple.cameraalbumtest.fileprovider",outputImage)
            }else{
                // 否则从文件创建Uri。
                Uri.fromFile(outputImage)
            }
            getImage.launch(imageUri)
        }
    }
}