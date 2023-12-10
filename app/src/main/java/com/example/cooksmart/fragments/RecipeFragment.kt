package com.example.cooksmart.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.utils.ListToCommaSeparate
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class RecipeFragment : Fragment(), IngredientFragmentListener, RecipeAdapter.OnClickListener {
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
        if (!isFetchingRecipes && isAdded) {
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
        val recipe = Recipe(recipeItem.id, recipeItem.title, recipeItem.image)
        addRecipeToHistory(recipe)
        startActivity(intent)
    }

    private fun addRecipeToHistory(recipe: Recipe) {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        Log.d("uid", uid.toString())
        val databaseReference = Firebase.database.reference
        uid?.let {
            val recipeHistoryRef = databaseReference.child("users").child(it).child("recipeHistory")
            recipeHistoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var isDuplicate = false
                    for (childSnapshot in snapshot.children) {
                        val existingRecipeId = childSnapshot.child("id").getValue(Int::class.java)
                        println(existingRecipeId)
                        if (existingRecipeId == recipe.id) {
                            // Duplicate found
                            isDuplicate = true
                            break
                        }
                    }
                    if (!isDuplicate) {
                        writeRecipeHistory(recipe, recipeHistoryRef)
                    } else {
                        Log.d("Recipe History", "RecipeId ${recipe.id} already exists in history.")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Recipe History", "Error checking recipe history: ${error.message}")
                }

            })
        }
    }

    fun writeRecipeHistory(recipe: Recipe, recipeHistoryRef: DatabaseReference) {
        val newRecipeRef = recipeHistoryRef.push()
        newRecipeRef.setValue(recipe).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(
                    "Recipe History", "RecipeId $recipe added to history successfully."
                )
            } else {
                Log.e(
                    "Recipe History",
                    "Failed to write recipeId $recipe to history: ${task.exception}"
                )
            }
        }
    }

    private fun retrieveRecipeHistory() {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeHistoryRef = databaseReference.child("users").child(it).child("recipeHistory")

            recipeHistoryRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Iterate through the snapshot to get recipeIds
                    for (childSnapshot in snapshot.children) {
                        val recipeId = childSnapshot.child("id").getValue(Int::class.java)
                        val imgUrl = childSnapshot.child("imgUrl").getValue(String::class.java)
                        val name = childSnapshot.child("name").getValue(String::class.java)
                        Log.d("RECIPE FROM FIREBASE", "$recipeId, $imgUrl, $name")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Recipe History", "Error retrieving recipe history: ${error.message}")
                }
            })
        }
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