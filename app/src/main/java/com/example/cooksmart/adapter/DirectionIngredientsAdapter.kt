package com.example.cooksmart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.api.model.instructions.Ingredient
import com.squareup.picasso.Picasso

class DirectionIngredientsAdapter(context: Context, private val list: List<Ingredient>) :
    RecyclerView.Adapter<DirectionIngredientsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val instructionsStepImg: ImageView = itemView.findViewById(R.id.instructionsStepImg)
        val instructionsStepTextView: TextView =
            itemView.findViewById(R.id.instructionsStepTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direction_used, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.instructionsStepTextView.text = list[position].name
        holder.instructionsStepTextView.isSelected = true
        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/${list[position].image}")
            .into(holder.instructionsStepImg)
    }
}