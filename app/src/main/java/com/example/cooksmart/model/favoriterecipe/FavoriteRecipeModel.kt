package com.example.cooksmart.model.favoriterecipe

import com.example.cooksmart.model.recipe.RecipeModel

class FavoriteRecipeModel() {
    private val favoriteRecipeList: ArrayList<RecipeModel> = ArrayList()


    fun getUserRecipeHistory(): ArrayList<RecipeModel> {
        return favoriteRecipeList
    }
    fun addRecipeToList(recipe: RecipeModel) {
        favoriteRecipeList.add(recipe)
    }

    fun setUserRecipeHistory(recipes: ArrayList<RecipeModel>) {
        favoriteRecipeList.clear()
        favoriteRecipeList.addAll(recipes)
    }
}