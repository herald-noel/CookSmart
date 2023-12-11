package com.example.cooksmart.model.favoriterecipe

import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.recipe.RecipeModel

class FavoriteRecipeModel(): Model() {
    private val favoriteRecipeList: ArrayList<RecipeModel> = ArrayList()
}