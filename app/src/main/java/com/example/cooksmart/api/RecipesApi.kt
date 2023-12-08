package com.example.cooksmart.api

import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.api.model.instructions.InstructionsResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApi {
        @GET("recipes/findByIngredients")
        fun getRecipesByIngredients(
            @Query("apiKey") api: String,
            @Query("ingredients") ingredients: String,
            @Query("number") number: Int,
            @Query("ranking") ranking: Int,
        ): Call<RecipeApiResponse>

        @GET("recipes/{id}/analyzedInstructions")
        fun getAnalyzedRecipeInstructions(
           @Path("id") id: Int,
           @Query("apiKey") api: String,
        ): Call<InstructionsResponse>
}