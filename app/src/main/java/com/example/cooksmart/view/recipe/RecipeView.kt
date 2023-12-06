package com.example.recipeview.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.adapter.InstructionAdapter
import com.example.cooksmart.api.model.RecipeApiResponseItem
import com.example.cooksmart.controller.recipe.RecipeController
import com.example.cooksmart.model.recipe.RecipeModel
import com.squareup.picasso.Picasso

class RecipeView(private val context: Context?, viewGroup: ViewGroup?) : IRecipeView {

    val view: View
    var model: RecipeModel
    val controller: RecipeController

    val recipeIV: ImageView
    val recipeName: TextView

    init {
        model = RecipeModel()
        controller = RecipeController(model, this)
        view = LayoutInflater.from(context).inflate(R.layout.recipe_view, viewGroup)

        recipeIV = view.findViewById(R.id.recipeIV)
        recipeName = view.findViewById(R.id.recipeNTV)

    }

    fun setImage(image: String){
        Picasso.get()
            .load(image)
            .into(recipeIV)
    }

    fun setName(name: String){
        recipeName.setText(name)
    }
    override fun getRootView(): View {
        return view
    }
}
