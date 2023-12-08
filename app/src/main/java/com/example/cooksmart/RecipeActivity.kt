package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.adapter.DirectionAdapter
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.view.recipe.RecipeView

class RecipeActivity : AppCompatActivity() {
    private lateinit var recipeView: RecipeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeView = RecipeView(this, null)
        intent.getStringExtra("recipeId")?.let { recipeView.setId(it.toInt()) }
        recipeView.setName(intent.getStringExtra("recipeName").toString())
        recipeView.setImage(intent.getStringExtra("recipeImageUrl").toString())

        setContentView(recipeView.getRootView())
    }


}