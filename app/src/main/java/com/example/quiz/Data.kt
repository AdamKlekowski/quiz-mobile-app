package com.example.quiz

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley.newRequestQueue



object Data {
    lateinit var queue: RequestQueue
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
        loadCategories()
    }

    fun loadQuestions(category: String, numToDownload: String, contect: Context, intent: Intent) {
        val url = "https://opentdb.com/api.php?amount=%s&type=multiple&category=%s&token=%s".format(
            numToDownload, categories[category], token)
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
                contect.startActivity(intent)
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

    private fun loadCategories() {
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
            },
            {
            })
        queue.add(categoriesRequest)
    }
}