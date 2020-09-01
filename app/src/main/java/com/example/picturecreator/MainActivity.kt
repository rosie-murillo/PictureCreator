package com.example.picturecreator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_TAKE_PHOTO = 1
    }

    lateinit var folderName: EditText
    lateinit var cameraPicture: Button
    lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraPicture = findViewById(R.id.button)
        folderName = findViewById(R.id.editTextTextPersonName)

        cameraPicture.setOnClickListener {
            dispatchTakePictureIntent()

        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try{
                    createImageFile()
                }catch (ex: IOException){
                    //error while creating the file
                    null
                }
                //continue only if the file was succesfully created
                photoFile?.also {
                 val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        ///storage/emulated/0/Android/data/com.example.picturecreator/files/Pictures
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        Log.d("PICTURE TAKER", "path $storageDir")
        return File.createTempFile(
            "JPEG_${timeStamp}",  /*prefix*/
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

}