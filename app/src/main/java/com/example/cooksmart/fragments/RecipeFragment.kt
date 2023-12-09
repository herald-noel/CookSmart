package com.example.cooksmart.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.data.Ingredient
import com.example.cooksmart.IngredientFragmentListener
import com.example.cooksmart.R
import com.example.cooksmart.RecipeActivity
import com.example.cooksmart.adapter.RecipeAdapter
import com.example.cooksmart.api.RequestManager
import com.example.cooksmart.api.listener.RecipeResponseListener
import com.example.cooksmart.api.model.RecipeApiResponse
import com.example.cooksmart.api.model.RecipeApiResponseItem
import com.example.cooksmart.utils.ListToCommaSeparate

class RecipeFragment() : Fragment(),
    IngredientFragmentListener, RecipeAdapter.OnClickListener {
    private lateinit var ingredientList: MutableList<Ingredient>
    private lateinit var recipeAdapter: RecipeAdapter
    private var lastIngredients: String? = null
    private var isFetchingRecipes = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelableArrayList<Ingredient>("ingredients_key")?.let {
            // Initialize ingredientList with the retrieved ArrayList
            ingredientList = it.toMutableList()
        } ?: run {
            // Handle case where ingredients_key is not present in arguments
            ingredientList = mutableListOf()
        }

        val ingredients = ListToCommaSeparate.convertToString(ingredientList)

        val recyclerview: RecyclerView = view.findViewById(R.id.recycler_recipe_home)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        recipeAdapter = RecipeAdapter(RecipeApiResponse(), this)
        recyclerview.adapter = recipeAdapter
        if (ingredients != lastIngredients) {
            lastIngredients = ingredients
            managerGetRecipe(ingredients)
        }
    }

    private fun managerGetRecipe(ingredients: String) {
        if (!isFetchingRecipes) {
            isFetchingRecipes = true
            val manager = RequestManager(requireContext())
            manager.getRecipes(recipeResponseListener, ingredients)
        }
    }


    private val recipeResponseListener: RecipeResponseListener = object : RecipeResponseListener {
        override fun didFetch(response: RecipeApiResponse, message: String) {
            recipeAdapter.updateData(response)
            isFetchingRecipes = false
        }

        override fun didError(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onIngredientChange(updatedIngredients: MutableList<Ingredient>) {
        ingredientList = updatedIngredients
        val ingredients = ListToCommaSeparate.convertToString(updatedIngredients)
        managerGetRecipe(ingredients)
    }

    override fun onRecipeClick(recipeItem: RecipeApiResponseItem) {
        val intent = Intent(requireContext(), RecipeActivity::class.java)
        intent.putExtra("recipeId", recipeItem.id)
        intent.putExtra("recipeName", recipeItem.title)
        intent.putExtra("recipeImageUrl", recipeItem.image)
        startActivity(intent)
    }

    companion object {
        fun newInstance(ingredientList: ArrayList<Ingredient>): RecipeFragment {
            val fragment = RecipeFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("ingredients_key", ingredientList)
            fragment.arguments = bundle
            return fragment
        }
    }
}