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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    val takePhoto = 1
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val takePhotoBtn: Button = findViewById(R.id.takePhotoBtn)
        takePhotoBtn.setOnClickListener {
            // 创建File对象用于存储拍的照片,放在当前应用缓存数据的位置
            outputImage = File(externalCacheDir,"output_image.jpg")
            if (outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            // 检查系统是否低于Android7
            imageUri = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                // 低于就转为Uri对象
                FileProvider.getUriForFile(this,"com.exa" +
                        "mple.cameraalbumtest.fileprovider",outputImage)
            }else{
                // 否则从文件创建Uri。
                Uri.fromFile(outputImage)
            }
            // 启动相机程序
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            // 指定图片的输出地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            // 新的写法
//            registerForActivityResult(intent,takePhoto)
            startActivityForResult(intent,takePhoto)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var imageView: ImageView = findViewById(R.id.imageView)
        when (requestCode){
            takePhoto->{
                if (resultCode == Activity.RESULT_OK){
                    // 将拍摄的照片显示出来
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    imageView.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when(orientation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap,90)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
            matrix, true)
        // 将不再需要的Bitmap对象回收
        bitmap.recycle()
        return rotatedBitmap
    }
}