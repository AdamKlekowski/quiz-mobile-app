package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultsActivity : AppCompatActivity() {
    private lateinit var mainMsg: TextView
    private lateinit var score: TextView
    private lateinit var nextBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val points = intent.getIntExtra("points", 0)
        val total = intent.getIntExtra("total", 10)

        mainMsg = findViewById(R.id.results_main_msg)
        mainMsg.text = if (points.toDouble()/total.toDouble() > 0.8) {
            "Congratulations!"
        } else if (points.toDouble()/total.toDouble() > 0.5) {
            "Good!"
        } else {
            "Try once more!"
        }

        score = findViewById(R.id.score)
        score.text = "Your score: $points/$total points."

        nextBtn = findViewById(R.id.back_to_main)
        nextBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}