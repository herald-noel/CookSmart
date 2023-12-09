package com.example.cooksmart.model.pastrecipe

import com.example.cooksmart.model.recipe.RecipeModel

class PastRecipeModel {
    // Private backing field to store the recipe history
    private val pastRecipeHistory: ArrayList<RecipeModel> = ArrayList()

    // Getter method for userRecipeHistory property
    fun getUserRecipeHistory(): ArrayList<RecipeModel> {
        return pastRecipeHistory
    }

    // Optional: Add a method to add a recipe to the history
    fun addRecipeToHistory(recipe: RecipeModel) {
        pastRecipeHistory.add(recipe)
    }
    fun setUserRecipeHistory(recipes: ArrayList<RecipeModel>) {
        pastRecipeHistory.clear()
        pastRecipeHistory.addAll(recipes)
    }

}