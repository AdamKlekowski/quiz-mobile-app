package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class NetwrokErrorAcitivity : AppCompatActivity() {
    private lateinit var errorIcon: ImageView
    private lateinit var backBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netwrok_error_acitivity)

        errorIcon = findViewById(R.id.error_icon)
        errorIcon.setImageResource(R.drawable.network_error_icon)

        backBtn = findViewById(R.id.erro_back_btn)
        backBtn.setOnClickListener {
            finish()
        }
    }
}