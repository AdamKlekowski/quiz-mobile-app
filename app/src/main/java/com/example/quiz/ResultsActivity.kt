package com.example.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
        mainMsg.text = when {
            points.toDouble()/total.toDouble() > 0.8 -> {
                "Congratulations!"
            }
            points.toDouble()/total.toDouble() > 0.5 -> {
                "Good!"
            }
            else -> {
                "Try once more!"
            }
        }

        score = findViewById(R.id.score)
        score.text = "$points/$total points"

        val difficultyLevel = intent.getStringExtra("difficultyLevel")
        Log.println(Log.WARN, "RESULTS", difficultyLevel.toString())
        val prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)

        val currentValue = ((points.toDouble()/total.toDouble())*100).toInt()
        val currentRecord = prefs.getInt("top_score_$difficultyLevel", 0)

        Log.println(Log.WARN, "RESULTS", currentValue.toString())
        Log.println(Log.WARN, "RESULTS", currentRecord.toString())

        if (currentValue > currentRecord) {
            Log.println(Log.WARN, "RESULTS", "work")
            val editor = prefs.edit()
            editor.putInt("top_score_$difficultyLevel", currentValue)
            editor.apply()
        }

        nextBtn = findViewById(R.id.back_to_main)
        nextBtn.setOnClickListener {
            finish()
        }
    }
}