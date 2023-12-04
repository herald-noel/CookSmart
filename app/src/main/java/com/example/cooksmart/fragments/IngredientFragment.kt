package com.example.cooksmart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.Ingredient
import com.example.cooksmart.IngredientAdapter
import com.example.cooksmart.R
class IngredientFragment : Fragment() {

    private val ingredientList: ArrayList<Ingredient> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add sample ingredients to the list
        ingredientList.add(Ingredient("Ingredient 1"))
        ingredientList.add(Ingredient("Ingredient 2"))
        ingredientList.add(Ingredient("Ingredient 3"))

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapter = IngredientAdapter(ingredientList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
