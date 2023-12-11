package com.example.cooksmart.model.pastrecipe

import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.recipe.RecipeModel

class PastRecipeModel(): Model() {
    // Private backing field to store the recipe history
    private val pastRecipeHistory: ArrayList<RecipeModel> = ArrayList()

}