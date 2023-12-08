package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.recipe.RecipeView

class RecipeActivity : AppCompatActivity() {
    private lateinit var recipeView: RecipeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeView = RecipeView(this, null)
        recipeView.initInstructions(intent.getIntExtra("recipeId", 0))
        recipeView.setName(intent.getStringExtra("recipeName").toString())
        recipeView.setImage(intent.getStringExtra("recipeImageUrl").toString())

        setContentView(recipeView.getRootView())
    }


}