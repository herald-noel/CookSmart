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
        //recipeView = RecipeView(this, null)

        setContentView(R.layout.activity_recipe)
//        val recipeName = intent.getStringExtra("recipeName").toString()
//        val recipeImgUrl = intent.getStringExtra("recipeImageUrl").toString()
//        recipeView.setName(recipeName)
//        recipeView.setImage(recipeImgUrl)


        val recyclerView: RecyclerView= findViewById(R.id.recyclerView_directions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DirectionAdapter(arrayListOf("test", "test1"))
    }
}