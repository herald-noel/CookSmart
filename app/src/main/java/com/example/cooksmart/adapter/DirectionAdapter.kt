package com.example.cooksmart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.api.model.instructions.InstructionsResponseItem
import com.example.cooksmart.api.model.instructions.Step

class DirectionAdapter(private val instructions: ArrayList<Step>) :
    RecyclerView.Adapter<DirectionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val directionNumber: TextView = itemView.findViewById(R.id.direction_num)
        val direction: TextView = itemView.findViewById(R.id.direction_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direction, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val steps = instructions[position]

        // sets the text to the textview from our itemHolder class
        holder.directionNumber.text = steps.number.toString()
        holder.direction.text = steps.step
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

}