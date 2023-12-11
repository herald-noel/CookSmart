package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.favoriteRecipe.FavoriteRecipeView
import com.example.cooksmart.view.recipeHistory.RecipeHistoryView

class RecipeHistoryActivity : AppCompatActivity() {
    private lateinit var recipeHistoryView: RecipeHistoryView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeHistoryView = RecipeHistoryView(this, null)
        setContentView(recipeHistoryView.getRootView())
    }
}