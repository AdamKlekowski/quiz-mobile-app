package com.example.quiz

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley.newRequestQueue

object Data {
    private lateinit var queue: RequestQueue
    private lateinit var categories: Map<String, Int>
    private var token: String? = null


    fun prepareData(context: Context) {
        queue = newRequestQueue(context)
        loadToken()
        loadCategories()
    }

    fun loadQuestions(category: String, numToDownload: Int) {
        TODO()
    }

    private fun loadToken() {
        val url = "https://opentdb.com/api_token.php?command=request"

        val tokenRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                token = response.getString("token")
                Log.println(Log.DEBUG, "Data", token.toString())
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
                Log.println(Log.DEBUG, "Data", categories.toString())

            },
            {
            })
        queue.add(categoriesRequest)
    }
}