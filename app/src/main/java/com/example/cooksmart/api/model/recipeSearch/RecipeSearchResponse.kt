package com.example.cooksmart.api.model.recipeSearch

data class RecipeSearchResponse(
    val offset: Int,
    val number: Int,
    val results: ArrayList<Result>,
    val totalResults: Int
)
