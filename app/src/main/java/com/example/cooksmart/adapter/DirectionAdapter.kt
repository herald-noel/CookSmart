package com.example.cooksmart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.api.model.instructions.Step

class DirectionAdapter(private val context: Context, private val instructions: ArrayList<Step>) :
    RecyclerView.Adapter<DirectionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val directionNumber: TextView = itemView.findViewById(R.id.direction_num)
        val direction: TextView = itemView.findViewById(R.id.direction_text)
        val recyclerViewRecipeEquipment: RecyclerView =
            itemView.findViewById(R.id.recycler_recipe_equipment)
        val recyclerViewRecipeIngredient: RecyclerView =
            itemView.findViewById(R.id.recycler_recipe_ingredient)
        val ingredientLinearLayout: LinearLayout =
            itemView.findViewById(R.id.ingredient_linearLayout)
        val equipmentLinearLayout: LinearLayout = itemView.findViewById(R.id.equipment_linearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val steps = instructions[position]

        holder.directionNumber.text = steps.number.toString()
        holder.direction.text = steps.step

        if (steps.ingredients.size != 0) {
            holder.recyclerViewRecipeIngredient.setHasFixedSize(true)
            holder.recyclerViewRecipeIngredient.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            holder.recyclerViewRecipeIngredient.adapter =
                DirectionIngredientsAdapter(context, steps.ingredients)
        } else {
            holder.ingredientLinearLayout.visibility = LinearLayout.GONE
        }

        if (steps.equipment.size != 0) {

        holder.recyclerViewRecipeEquipment.setHasFixedSize(true)
        holder.recyclerViewRecipeEquipment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewRecipeEquipment.adapter =
            DirectionEquipmentAdapter(context, steps.equipment)
        } else {
            holder.equipmentLinearLayout.visibility = LinearLayout.GONE
        }
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

}