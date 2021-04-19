package com.example.quiz

import android.content.Context
import android.content.Intent
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley.newRequestQueue



object Data {
    private lateinit var queue: RequestQueue
    @Volatile
    lateinit var categories: Map<String, Int>
    @Volatile
    var token: String? = null
    @Volatile
    var questions: List<Question> = emptyList()


    fun prepareData(context: Context) {
        queue = newRequestQueue(context)
        queue.start()
        loadToken()
        categories = emptyMap<String, Int>().toMutableMap()
    }

    fun loadQuestions(category: String, numToDownload: String, difficultyLevel: String, context: Context) {
        val intent = Intent(context, LoadingActivity::class.java).apply {}
        context.startActivity(intent)

        val url = if (token == null) {
            "https://opentdb.com/api.php?amount=%s&type=multiple&category=%s&difficulty=%s".format(
                numToDownload, categories[category], difficultyLevel)
        } else {
            "https://opentdb.com/api.php?amount=%s&type=multiple&category=%s&token=%s&difficulty=%s".format(
                numToDownload, categories[category], token, difficultyLevel)
        }

        val tmpList = emptyList<Question>().toMutableList()

        val tokenRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val questionsArray = response.getJSONArray("results")
                for (i in 0 until questionsArray.length()) {
                    val question = questionsArray.getJSONObject(i).getString("question")
                    val correctAnswer = questionsArray.getJSONObject(i).getString("correct_answer")
                    val incorrectAnswers = questionsArray.getJSONObject(i).getJSONArray("incorrect_answers")
                    val incorrectAnswer1 = incorrectAnswers.getString(0)
                    val incorrectAnswer2 = incorrectAnswers.getString(1)
                    val incorrectAnswer3 = incorrectAnswers.getString(2)
                    tmpList.add(Question(question, correctAnswer, incorrectAnswer1, incorrectAnswer2, incorrectAnswer3))
                }
                questions = tmpList
                val quizIntent = Intent(context, QuizActivity::class.java).apply {}
                context.startActivity(quizIntent)
            },
            {
            })
        queue.add(tokenRequest)
    }

    private fun loadToken() {
        val url = "https://opentdb.com/api_token.php?command=request"

        val tokenRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                token = response.getString("token")
            },
            {
            })
        queue.add(tokenRequest)
    }

    fun loadCategories(contect: Context) {
        val intent = Intent(contect, LoadingActivity::class.java).apply {}
        contect.startActivity(intent)

        val url = "https://opentdb.com/api_category.php"
        val tmpMap = emptyMap<String, Int>().toMutableMap()

        val categoriesRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val categoriesArray = response.getJSONArray("trivia_categories")
                for (i in 0 until categoriesArray.length()) {
                    val category = categoriesArray.getJSONObject(i)
                    val name = category.getString("name")
                    val id = category.getInt("id")
                    tmpMap[name] = id
                }
                categories = tmpMap
                val quizIntent = Intent(contect, ChoiceQuizTypeActivity::class.java).apply {}
                contect.startActivity(quizIntent)
            },
            {
            })
        queue.add(categoriesRequest)
    }
}