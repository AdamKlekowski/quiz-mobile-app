package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.coroutines.*

class ChoiceQuizTypeActivity : AppCompatActivity() {
    private lateinit var categorySpinner: Spinner
    private lateinit var numbersSpinner: Spinner
    private lateinit var difficultySpinner: Spinner
    private lateinit var playButton: Button

    private var selectedCategories: String = ""
    private var selectedNumber: String = ""
    private var selectedDifficulty: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_quiz_type)

        categorySpinner = findViewById(R.id.category)
        val categoryArraySpinner : ArrayList<String> = ArrayList()

        for (elem in Data.categories.keys) {
            categoryArraySpinner.add(elem)
        }
        categorySpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, categoryArraySpinner)
        categorySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedCategories = categoryArraySpinner[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        numbersSpinner = findViewById(R.id.question_numbers)
        val numbersArraySpinner : ArrayList<String> = ArrayList()
        numbersArraySpinner.add("10")
        numbersArraySpinner.add("20")
        numbersArraySpinner.add("30")
        numbersArraySpinner.add("40")
        numbersArraySpinner.add("50")
        numbersSpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, numbersArraySpinner)
        numbersSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedNumber = numbersArraySpinner[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        difficultySpinner = findViewById(R.id.difficulty_level)
        val difficultyArraySpinner : ArrayList<String> = ArrayList()
        difficultyArraySpinner.add("easy")
        difficultyArraySpinner.add("medium")
        difficultyArraySpinner.add("hard")
        difficultySpinner.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, difficultyArraySpinner)


        playButton = findViewById(R.id.play_btn)
        playButton.setOnClickListener {
            Data.loadQuestions(selectedCategories, selectedNumber)
            val intent = Intent(this, QuizActivity::class.java).apply {}

            waitFun()

            startActivity(intent)
        }
    }

    private fun waitFun() = runBlocking {
        Log.println(Log.WARN, "Chioce", Data.questions.toString())
        val delayCoroutine = launch {
            delay(1000)
        }
        delayCoroutine.join()
    }
}