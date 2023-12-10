package com.example.cooksmart.controller.recipe

import android.util.Log
import android.widget.Toast
import com.example.cooksmart.api.RequestManager
import com.example.cooksmart.api.listener.InstructionsListener
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.view.recipe.RecipeView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class RecipeController(
    private val recipeView: RecipeView
) : Controller() {

    fun addRecipeFavoriteFirebase(recipe: Recipe) {
        addRecipeToFavorites(recipe)
    }

    private fun addRecipeToFavorites(recipe: Recipe) {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        Log.d("uid", uid.toString())
        val databaseReference = Firebase.database.reference
        uid?.let {
            val recipeHistoryRef =
                databaseReference.child("users").child(it).child("recipeFavorites")
            recipeHistoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var isDuplicate = false
                    for (childSnapshot in snapshot.children) {
                        val existingRecipeId = childSnapshot.child("id").getValue(Int::class.java)
                        if (existingRecipeId == recipe.id) {
                            isDuplicate = true
                            break
                        }
                    }
                    if (!isDuplicate) {
                        writeRecipeFavorite(recipe, recipeHistoryRef)
                        Toast.makeText(
                            recipeView.context,
                            "Successfully added to favorites.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.d(
                            "Recipe Favorite",
                            "RecipeId ${recipe.id} already exists in favorites."
                        )
                        Toast.makeText(
                            recipeView.context,
                            "Already added in favorites.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Recipe Favorite", "Error checking recipe favorite: ${error.message}")
                }

            })
        }
    }

    fun writeRecipeFavorite(recipe: Recipe, recipeHistoryRef: DatabaseReference) {
        val newRecipeRef = recipeHistoryRef.child(recipe.id.toString())
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

    fun managerGetRecipeDirection(id: Int) {
        val manager = RequestManager(recipeView.context)
        manager.getInstructions(instructionsListener, id)
    }

    private val instructionsListener: InstructionsListener = object : InstructionsListener {
        override fun didFetch(response: InstructionsResponse, message: String) {
            Log.d("DIRECTIONS SUCCESS", response.toString())
            recipeView.setAdapter(response)
        }

        override fun didError(message: String) {
            Toast.makeText(recipeView.context, message, Toast.LENGTH_LONG).show()
        }
    }
}