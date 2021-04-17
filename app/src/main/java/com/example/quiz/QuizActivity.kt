package com.example.quiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity() {
    private lateinit var question: TextView
    private lateinit var no_indicitor: TextView
    private lateinit var answer1Button: Button
    private lateinit var answer2Button: Button
    private lateinit var answer3Button: Button
    private lateinit var answer4Button: Button
    private lateinit var nextButton: Button
    private var choosenAnswer: String = ""
    private var no: Int = 0
    private var points: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        no_indicitor = findViewById(R.id.no_indicator)

        question = findViewById(R.id.question)

        answer1Button = findViewById(R.id.buttonA)
        answer1Button.setOnClickListener {
            clearAllButton()
            answer1Button.setBackgroundResource(R.drawable.selected_button)
            choosenAnswer = answer1Button.text.toString()
        }

        answer2Button = findViewById(R.id.buttonB)
        answer2Button.setOnClickListener {
            clearAllButton()
            answer2Button.setBackgroundResource(R.drawable.selected_button)
            choosenAnswer = answer2Button.text.toString()
        }

        answer3Button = findViewById(R.id.buttonC)
        answer3Button.setOnClickListener {
            clearAllButton()
            answer3Button.setBackgroundResource(R.drawable.selected_button)
            choosenAnswer = answer3Button.text.toString()
        }

        answer4Button = findViewById(R.id.buttonD)
        answer4Button.setOnClickListener {
            clearAllButton()
            answer4Button.setBackgroundResource(R.drawable.selected_button)
            choosenAnswer = answer4Button.text.toString()
        }

        updateQuestionsAndAnswers()

        nextButton = findViewById(R.id.next_btn)
        nextButton.setOnClickListener {
            no += 1
            if (no > Data.questions.size) {
                TODO()
            }
            updateQuestionsAndAnswers()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestionsAndAnswers() {
        if (no > 0) {
            validateAnswers()
        }

        clearAllButton()
        no_indicitor.text = (no+1).toString() + "/" + Data.questions.size.toString()
        question.text = Data.questions[no].question
        answer1Button.text = Data.questions[no].correctAnswer
        answer2Button.text = Data.questions[no].incorrectAnswer1
        answer3Button.text = Data.questions[no].incorrectAnswer2
        answer4Button.text = Data.questions[no].incorrectAnswer3
    }

    private fun validateAnswers() {
        if (choosenAnswer == Data.questions[no].correctAnswer) {
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