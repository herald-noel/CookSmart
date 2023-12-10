package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.recipe.RecipeView

class RecipeActivity : AppCompatActivity() {
    private lateinit var recipeView: RecipeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipeId = intent.getIntExtra("recipeId", 0)
        val recipeName = intent.getStringExtra("recipeName").toString()
        val recipeImg = intent.getStringExtra("recipeImageUrl").toString()
        val recipeImgType = intent.getStringExtra("recipeImgType").toString()
        recipeView = RecipeView(
            this, null,
            recipeId, recipeName, recipeImg, recipeImgType
        )
        setContentView(recipeView.getRootView())
    }


}