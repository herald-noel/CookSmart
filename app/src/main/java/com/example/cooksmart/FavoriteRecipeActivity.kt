package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.favoriteRecipe.FavoriteRecipeView

class FavoriteRecipeActivity : AppCompatActivity() {
    private lateinit var favoriteRecipeView: FavoriteRecipeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteRecipeView = FavoriteRecipeView(this, null)
        setContentView(favoriteRecipeView.getRootView())
    }
}