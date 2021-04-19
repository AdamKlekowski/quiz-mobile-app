package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private lateinit var playBtn: Button
    private lateinit var recordsBtn: Button
    private lateinit var exitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Data.prepareData(applicationContext)

        playBtn = findViewById(R.id.play_btn)
        playBtn.setOnClickListener {
            Data.loadCategories(this)
        }

        recordsBtn = findViewById(R.id.recordsButton)
        recordsBtn.setOnClickListener {  }

        exitBtn = findViewById(R.id.exitButton)
        exitBtn.setOnClickListener {
            finish()
        }
    }
}