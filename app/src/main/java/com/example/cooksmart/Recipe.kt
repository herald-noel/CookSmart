package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cooksmart.adapter.DirectionAdapter
import com.example.cooksmart.adapter.InstructionAdapter
import com.example.cooksmart.view.recipe.RecipeView

class Recipe : AppCompatActivity() {
    private lateinit var recipeView: RecipeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeView = RecipeView(this, null)
        recipeView.setName(intent.getStringExtra("recipeName").toString())
        recipeView.setImage(intent.getStringExtra("recipeImageUrl").toString())
        setContentView(recipeView.getRootView())

        val recyclerView: RecyclerView= findViewById(R.id.recyclerV_Inst)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val stepsArray = resources.getStringArray(R.array.recipe_1_steps)
        val stepsList = stepsArray.toList()
        val stepsArrayList = ArrayList(stepsList)
        recyclerView.adapter = DirectionAdapter(stepsArrayList)
    }
}