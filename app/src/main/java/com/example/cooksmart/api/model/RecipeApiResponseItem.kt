package com.example.cooksmart.api.model

data class RecipeApiResponseItem(
        val id: Int,
        val image: String,
        val imageType: String,
        val likes: Int,
        val missedIngredientCount: Int,
        val missedIngredients: List<MissedIngredient>,
        val title: String,
        val unusedIngredients: List<UnusedIngredient>,
        val usedIngredientCount: Int,
        val usedIngredients: List<UsedIngredient>?
)
