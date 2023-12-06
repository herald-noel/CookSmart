package com.example.cooksmart.api

import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.RecipeApiResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {
        @GET("recipes/findByIngredients")
        fun getRecipesByIngredients(
            @Query("apiKey") api: String,
            @Query("ingredients") ingredients: String,
            @Query("number") number: Int,
            @Query("ranking") ranking: Int,
        ): Call<RecipeApiResponse>
}