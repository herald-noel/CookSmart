package com.example.cooksmart.view.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.adapter.DirectionAdapter
import com.example.cooksmart.adapter.InstructionAdapter
import com.example.recipeview.view.IRecipeView
import com.squareup.picasso.Picasso

class RecipeView(val context: Context, viewGroup: ViewGroup?) : IRecipeView {

    val view: View
    private val recipeIV: ImageView
    private val recipeName: TextView
    private val recyclerView: RecyclerView

    init {
        view = LayoutInflater.from(context).inflate(R.layout.recipe_view, viewGroup)

        recipeIV = view.findViewById(R.id.recipeIV)
        recipeName = view.findViewById(R.id.recipeNTV)

        recyclerView = view.findViewById(R.id.recyclerV_Inst)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun setImage(image: String){
        Picasso.get()
            .load(image)
            .into(recipeIV)
    }

    fun setName(name: String){
        recipeName.text = name
    }

    override fun getRootView(): View {
        return view
    }

}
