package com.example.cooksmart.api.listener

import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.recipeSearch.RecipeSearchResponse

interface RecipeSearchListener {
    fun didFetch(response: RecipeSearchResponse, message: String)
    fun didError(message: String)
}