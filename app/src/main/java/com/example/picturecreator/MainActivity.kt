package com.example.picturecreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var folderName: EditText
    lateinit var cameraPicture: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraPicture = findViewById(R.id.button)
        folderName = findViewById(R.id.editTextTextPersonName)

        cameraPicture.setOnClickListener {

        }
    }
}