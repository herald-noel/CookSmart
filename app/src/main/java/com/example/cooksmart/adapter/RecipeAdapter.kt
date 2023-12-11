package com.example.cooksmart.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.RecipeApiResponseItem
import com.example.cooksmart.api.model.recipeSearch.RecipeSearchResponse
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private var recipeResponseList: RecipeApiResponse,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onRecipeClick(recipeItem: RecipeApiResponseItem)
    }

    fun updateData(newResponse: RecipeApiResponse) {
        // Update the response data and notify the adapter
        recipeResponseList = newResponse
        notifyDataSetChanged()
    }

    fun updateDataRecipeFromSearch(newResponse: RecipeSearchResponse) {
        // Update the response data and notify the adapter
        Log.d("response", newResponse.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeItem = recipeResponseList[position]
        holder.recipeName.text = recipeItem.title
        Picasso.get()
            .load(recipeItem.image)
            .into(holder.imageView)

        holder.cardView.setOnClickListener {
            onClickListener.onRecipeClick(recipeItem)
        }
    }

    override fun getItemCount(): Int {
        return recipeResponseList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.text_recipe_name)
        val imageView: ImageView = itemView.findViewById(R.id.recipeImage)
        val cardView: CardView = itemView.findViewById(R.id.card)
    }
}