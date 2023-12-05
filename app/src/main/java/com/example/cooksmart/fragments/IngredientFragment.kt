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

    private lateinit var ingredientList: ArrayList<Ingredient>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        // Use the ingredientList in the adapter
        val adapter = IngredientAdapter(ingredientList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        // Create a newInstance method to pass the ingredientList to the Fragment
        fun newInstance(ingredientList: ArrayList<Ingredient>): IngredientFragment {
            val fragment = IngredientFragment()
            fragment.ingredientList = ingredientList
            return fragment
        }
    }
}

