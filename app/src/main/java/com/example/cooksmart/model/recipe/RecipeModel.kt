package com.example.cooksmart.model.recipe

import com.example.cooksmart.R

data class RecipeModel(
    var id: Int = 0,
    var image: String = "",
    var imageType: String = "",
    var likes: Int = 0,
    var title: String = "",
    var missedIngredients: Int = 0,
    var usedIngredients: Int = 0,
    //val missedIngredientsList: List<IngredientModel>,
    //val unusedIngredientsList: List<IngredientModel>,
    //val usedIngredientsList: List<IngredientModel>
) {
    fun setRecipeDetails(
        recipeId: Int,
        recipeImage: String,
        recipeImageType: String,
        recipeLikes: Int,
        recipeTitle: String,
        recipeMissedIngredients: Int,
        recipeUsedIngredients: Int
    ) {
        id = recipeId
        image = recipeImage
        imageType = recipeImageType
        likes = recipeLikes
        title = recipeTitle
        missedIngredients = recipeMissedIngredients
        usedIngredients = recipeUsedIngredients
    }
}