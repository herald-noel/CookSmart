package com.example.cooksmart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R

class InstructionAdapter(private val instruction: List<Array<String>>) : RecyclerView.Adapter<InstructionAdapter.ListHolder>(){

    class ListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val instN: TextView = itemView.findViewById(R.id.text_inst_num)
        val instText: TextView = itemView.findViewById(R.id.text_instruction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_view, parent, false)
        return ListHolder(itemView)
    }

    override fun getItemCount(): Int {
        return instruction.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentInstruction = instruction[position]
        val stepText = currentInstruction.joinToString(separator = "\n") // Join array items with newline

        holder.instText.text = stepText
    }
}