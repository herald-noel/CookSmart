package com.example.cooksmart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter(
    private val ingredientList: MutableList<Ingredient>,
    private val onRemoveClickListener: OnRemoveClickListener
) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    interface OnRemoveClickListener {
        fun onRemoveClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.text_ingredient_name)
        val removeIcon: ImageView = itemView.findViewById(R.id.icon_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.ingredientName.text = ingredient.name

        // Handle remove button click
        holder.removeIcon.setOnClickListener {
            onRemoveClickListener.onRemoveClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
}

