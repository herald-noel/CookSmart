package com.example.cooksmart.model.profile

import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.favoriterecipe.FavoriteRecipeModel
import com.example.cooksmart.model.pastrecipe.PastRecipeModel
import com.example.cooksmart.model.recipe.RecipeModel

class ProfileModel : Model(), IProfileModel{
    var name = String()
    var email = String()
    var birthdate = String()
    var pastRecipes = PastRecipeModel()
    var favoriteRecipes = FavoriteRecipeModel()

    fun fetchEmail(): String {
        return email
    }
}