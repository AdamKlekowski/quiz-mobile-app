package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


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
            val intent = Intent(this, ChoiceQuizTypeActivity::class.java).apply {}
            startActivity(intent)
        }

        recordsBtn = findViewById(R.id.recordsButton)
        recordsBtn.setOnClickListener {  }

        exitBtn = findViewById(R.id.exitButton)
        exitBtn.setOnClickListener {
            finish()
        }
    }
}