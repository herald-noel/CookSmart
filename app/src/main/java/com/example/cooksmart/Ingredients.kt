package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.adapter.CustomAdapter
import com.example.cooksmart.api.RequestManager
import com.example.cooksmart.api.listener.RecipeResponseListener
import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.RecipeApiResponseItem

class Ingredients : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val ingredients = "-apples"

        val manager = RequestManager(this)
        manager.getRecipes(recipeResponseListener, ingredients)
    }

    private val recipeResponseListener: RecipeResponseListener = object : RecipeResponseListener {
        override fun didFetch(response: RecipeApiResponse, message: String) {
            Log.d("OUTPUT", response.toString())
            val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
            recyclerview.layoutManager = LinearLayoutManager(applicationContext)
            recyclerview.adapter = CustomAdapter(response)
        }

        override fun didError(message: String) {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}