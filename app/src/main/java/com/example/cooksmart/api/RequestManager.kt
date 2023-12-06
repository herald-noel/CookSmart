package com.example.cooksmart.api

import android.content.Context
import android.util.Log
import com.example.cooksmart.R
import com.example.cooksmart.api.listener.RecipeResponseListener
import com.example.cooksmart.api.model.RecipeApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestManager(val context: Context) {
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: RecipesApi by lazy {
        retrofit.create(RecipesApi::class.java)
    }

    fun getRecipes(
            listener: RecipeResponseListener,
            ingredients: String,
            number: Int = 10,
            ranking: Int = 1
    ) {
        val callRecipes: RecipesApi = api
        val call: Call<RecipeApiResponse> =
                callRecipes.getRecipesByIngredients(
                        context.getString(R.string.api_key),
                        ingredients,
                        number,
                        ranking
                )
        call.enqueue(object : Callback<RecipeApiResponse> {
            override fun onResponse(
                    call: Call<RecipeApiResponse>,
                    response: Response<RecipeApiResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                Log.d("isSuccess", "This is a success: ${response.message()}")
                response.body()?.let { listener.didFetch(it, response.message()) }
            }

            override fun onFailure(call: Call<RecipeApiResponse>, t: Throwable) {
                t.message?.let { listener.didError(it) }
            }
        })
    }
}