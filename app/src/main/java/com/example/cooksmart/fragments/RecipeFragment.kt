package com.example.cooksmart.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.Ingredient
import com.example.cooksmart.IngredientFragmentListener
import com.example.cooksmart.R
import com.example.cooksmart.adapter.RecipeAdapter
import com.example.cooksmart.adapter.ViewPagerAdapter
import com.example.cooksmart.api.RequestManager
import com.example.cooksmart.api.listener.RecipeResponseListener
import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.utils.ListToCommaSeparate

class RecipeFragment(ingredientList: ArrayList<Ingredient>) : Fragment(), IngredientFragmentListener {
    private  var ingredientList: MutableList<Ingredient> = ingredientList
    private lateinit var recipeAdapter: RecipeAdapter
    private var lastIngredients: String? = null

    fun setReporter(fragment: IngredientFragment) {
        fragment.setIngredientFragmentListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingredients = ListToCommaSeparate.convertToString(ingredientList)

        val recyclerview: RecyclerView = view.findViewById(R.id.recycler_recipe_home)
        recyclerview.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }

        recipeAdapter = RecipeAdapter(RecipeApiResponse())
        recyclerview.adapter = recipeAdapter
        if (ingredients != lastIngredients) {
            lastIngredients = ingredients
            managerGetRecipe(ingredients)
        }
    }

    fun managerGetRecipe(ingredients: String) {
        val manager = RequestManager(requireContext())
        manager.getRecipes(recipeResponseListener, ingredients)
    }

    private val recipeResponseListener: RecipeResponseListener = object : RecipeResponseListener {
        override fun didFetch(response: RecipeApiResponse, message: String) {
            recipeAdapter.updateData(response)
        }

        override fun didError(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    fun updateIngredients(ingredients: List<Ingredient>) {
        ingredientList = ingredients.toMutableList()
    }

    override fun onIngredientChange(updatedIngredients: MutableList<Ingredient>) {
       ingredientList = updatedIngredients
        Log.d("updating", "ok")

        val ingredients = ListToCommaSeparate.convertToString(updatedIngredients)
        managerGetRecipe(ingredients)
    }
}