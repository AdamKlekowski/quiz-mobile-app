package com.example.quiz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class BestResultsActivity : AppCompatActivity() {
    private lateinit var topEasyTextView: TextView
    private lateinit var topMediumTextView: TextView
    private lateinit var topHardTextView: TextView

    private lateinit var backBtn: Button
    private lateinit var resetBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_results)

        val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)

        val topScoreEasy = prefs.getInt("top_score_easy", 0)
        topEasyTextView = findViewById(R.id.top_easy)
        topEasyTextView.text = "EASY LEVEL: $topScoreEasy %"

        val topScoreMedium = prefs.getInt("top_score_medium", 0)
        topMediumTextView = findViewById(R.id.top_medium)
        topMediumTextView.text = "MEDIUM LEVEL: $topScoreMedium %"

        val topScoreHard = prefs.getInt("top_score_hard", 0)
        topHardTextView = findViewById(R.id.top_hard)
        topHardTextView.text = "HARD LEVEL: $topScoreHard %"

        backBtn = findViewById(R.id.topScore_back_btn)
        backBtn.setOnClickListener {
            finish()
        }

        resetBtn = findViewById(R.id.topScore_reset_btn)
        resetBtn.setOnClickListener {
            val editor = prefs.edit()
            editor.putInt("top_score_easy", 0)
            editor.putInt("top_score_medium", 0)
            editor.putInt("top_score_hard", 0)
            editor.apply()
            this.recreate()
        }
    }
}