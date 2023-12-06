package com.example.cooksmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.adapter.InstructionAdapter
import com.example.cooksmart.view.recipe.RecipeView

class ViewRecipeActivity : AppCompatActivity() {
    private lateinit var recipeView: RecipeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.recipe_view)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerV_Inst)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemList = arrayListOf("test", "test1")
        val adapter = InstructionAdapter(itemList)
        recyclerView.adapter = adapter
    }
}
