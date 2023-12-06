package com.example.cooksmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.adapter.InstructionAdapter
import com.example.recipeview.view.RecipeView

class ViewRecipeActivity : ComponentActivity() {
    lateinit var recipeView: RecipeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeView = RecipeView(this, null)
        recipeView.setImage(intent.getStringExtra("recipeName").toString())
        recipeView.setImage(intent.getStringExtra("recipeImageUrl").toString())
        setContentView(R.layout.recipe_view)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerV_Inst)

        val stepsList = listOf(
            resources.getStringArray(R.array.recipe_1_steps),
            resources.getStringArray(R.array.recipe_2_steps)
        )

        val adapter = InstructionAdapter(stepsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }



}
