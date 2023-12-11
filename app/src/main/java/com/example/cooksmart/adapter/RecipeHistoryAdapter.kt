package com.example.cooksmart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.listener.OnClickedFavoriteRecipeListener
import com.squareup.picasso.Picasso

class RecipeHistoryAdapter(
    private val recipeHistory: ArrayList<Recipe>,
    private val onClickedFavoriteRecipeListener: OnClickedFavoriteRecipeListener
) :
    RecyclerView.Adapter<RecipeHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeCardView: CardView = itemView.findViewById(R.id.favoriteRecipeCardView)
        val recipeName: TextView = itemView.findViewById(R.id.favoriteRecipeName)
        val recipeImg: ImageView = itemView.findViewById(R.id.favoriteRecipeImg)
        val recipeId: TextView = itemView.findViewById(R.id.favoriteRecipeId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return recipeHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeHistory[position]

        holder.recipeId.text = recipe.id.toString()
        holder.recipeName.text = recipe.title
        Picasso.get()
            .load("https://spoonacular.com/recipeImages/${recipe.id}-480x360.${recipe.imageType}")
            .into(holder.recipeImg)

        holder.recipeCardView.setOnClickListener {
           onClickedFavoriteRecipeListener.onRecipeClicked(position)
        }
    }
}