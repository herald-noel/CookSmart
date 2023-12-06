package com.example.cooksmart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.RecipeApiResponseItem
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private var recipeResponseList: RecipeApiResponse,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    fun updateData(newResponse: RecipeApiResponse) {
        // Update the response data and notify the adapter
        recipeResponseList = newResponse
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
        holder.usedIngredient.text = "No. of used ingredients ${recipeItem.usedIngredientCount}"
        holder.missedIngredient.text =
            "No. of missed ingredients ${recipeItem.missedIngredientCount}"
        holder.unusedIngredient.text =
            "No. of unused ingredients ${recipeItem.unusedIngredientCount}"

        holder.itemView.setOnClickListener {
            listener.onItemClick(recipeItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(recipeItem: RecipeApiResponseItem)
    }

    override fun getItemCount(): Int {
        return recipeResponseList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.text_recipe_name)
        val imageView: ImageView = itemView.findViewById(R.id.recipeImage)
        val usedIngredient: TextView = itemView.findViewById(R.id.text_ingredient_used)
        val missedIngredient: TextView = itemView.findViewById(R.id.text_ingredient_missed)
        val unusedIngredient: TextView = itemView.findViewById(R.id.text_ingredient_unused)
    }
}