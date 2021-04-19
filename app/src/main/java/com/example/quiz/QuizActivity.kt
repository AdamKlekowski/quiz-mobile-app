package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat

class QuizActivity : AppCompatActivity() {
    private lateinit var question: TextView
    private lateinit var noIndicitor: TextView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var answer4Button: Button
    private lateinit var nextButton: Button
    private var chosenAnswer: String = ""
    private var no: Int = 0
    private var points: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        noIndicitor = findViewById(R.id.no_indicator)

        question = findViewById(R.id.question)

        answer1Button = findViewById(R.id.buttonA)
        answer1Button.setOnClickListener {
            clearAllButton()
            answer1Button.setBackgroundResource(R.drawable.selected_button)
            chosenAnswer = answer1Button.text.toString()
        }

        answer2Button = findViewById(R.id.buttonB)
        answer2Button.setOnClickListener {
            clearAllButton()
            answer2Button.setBackgroundResource(R.drawable.selected_button)
            chosenAnswer = answer2Button.text.toString()
        }

        answer3Button = findViewById(R.id.buttonC)
        answer3Button.setOnClickListener {
            clearAllButton()
            answer3Button.setBackgroundResource(R.drawable.selected_button)
            chosenAnswer = answer3Button.text.toString()
        }

        answer4Button = findViewById(R.id.buttonD)
        answer4Button.setOnClickListener {
            clearAllButton()
            answer4Button.setBackgroundResource(R.drawable.selected_button)
            chosenAnswer = answer4Button.text.toString()
        }

        updateQuestionsAndAnswers()

        nextButton = findViewById(R.id.next_btn)
        nextButton.setOnClickListener {
            if (no >= Data.questions.size-1) {
                validateAnswers()
                val intent = Intent(this, ResultsActivity::class.java).apply {}
                intent.putExtra("points", points)
                intent.putExtra("total", Data.questions.size)
                startActivity(intent)
                finish()
            } else {
                no += 1
                updateQuestionsAndAnswers()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestionsAndAnswers() {
        if (no > 0) {
            validateAnswers()
        }

        clearAllButton()
        noIndicitor.text = (no+1).toString() + "/" + Data.questions.size.toString()
        question.text = HtmlCompat.fromHtml(Data.questions[no].question, HtmlCompat.FROM_HTML_MODE_LEGACY)
        answer1Button.text = HtmlCompat.fromHtml(Data.questions[no].correctAnswer, HtmlCompat.FROM_HTML_MODE_LEGACY)
        answer2Button.text = HtmlCompat.fromHtml(Data.questions[no].incorrectAnswer1, HtmlCompat.FROM_HTML_MODE_LEGACY)
        answer3Button.text = HtmlCompat.fromHtml(Data.questions[no].incorrectAnswer2, HtmlCompat.FROM_HTML_MODE_LEGACY)
        answer4Button.text = HtmlCompat.fromHtml(Data.questions[no].incorrectAnswer3, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun validateAnswers() {
        if (chosenAnswer == Data.questions[no-1].correctAnswer) {
            points += 1
        }
    }

    private fun clearAllButton() {
        answer1Button.setBackgroundResource(R.drawable.rounded_button_corner)
        answer2Button.setBackgroundResource(R.drawable.rounded_button_corner)
        answer3Button.setBackgroundResource(R.drawable.rounded_button_corner)
        answer4Button.setBackgroundResource(R.drawable.rounded_button_corner)
    }
}
