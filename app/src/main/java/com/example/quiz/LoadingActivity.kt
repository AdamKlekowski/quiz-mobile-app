package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }
}