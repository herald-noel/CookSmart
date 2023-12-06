package com.example.cooksmart.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.Ingredient
import com.example.cooksmart.IngredientAdapter
import com.example.cooksmart.IngredientFragmentListener
import com.example.cooksmart.R

class IngredientFragment(ingredientList: ArrayList<Ingredient>) : Fragment(), IngredientAdapter.OnRemoveClickListener {

    private  var ingredientList: MutableList<Ingredient> = ingredientList
    private lateinit var adapter: IngredientAdapter
    private var listener: IngredientFragmentListener? = null

    fun setIngredientFragmentListener(listener: IngredientFragmentListener) {
        this.listener = listener
    }
    private fun notifyRecipe(updateIngredient: MutableList<Ingredient>) {
        Log.d("Inisde notify", "nice")
        listener?.onIngredientChange(updateIngredient)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        // Initialize the adapter with the ingredientList and this fragment as the listener
        adapter = IngredientAdapter(ingredientList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    // Implementation of the remove click listener
    override fun onRemoveClick(position: Int) {
        // Remove the item from the list
        ingredientList.removeAt(position)

        notifyRecipe(ingredientList)

        // Notify the adapter about the removal
        adapter.notifyItemRemoved(position)
    }
}


