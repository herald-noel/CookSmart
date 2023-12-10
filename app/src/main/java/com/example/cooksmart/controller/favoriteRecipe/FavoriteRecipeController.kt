package com.example.cooksmart.controller.favoriteRecipe

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.view.favoriteRecipe.FavoriteRecipeView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class FavoriteRecipeController(private val favoriteRecipeView: FavoriteRecipeView) : Controller() {
    val favoriteRecipes: ArrayList<Recipe> = ArrayList()

    fun retrieveRecipeFavorites() {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeHistoryRef =
                databaseReference.child("users").child(it).child("recipeFavorites")
            recipeHistoryRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Iterate through the snapshot to get recipeIds
                    for (childSnapshot in snapshot.children) {
                        getSnapShot(childSnapshot)
                    }
                    if (favoriteRecipes.size != 0) {
                        favoriteRecipeView.showFavoriteRecipes(favoriteRecipes)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Recipe History", "Error retrieving recipe history: ${error.message}")
                }
            })

        }

    }

    fun getSnapShot(childSnapshot: DataSnapshot) {
        val recipeId = childSnapshot.child("id").getValue(Int::class.java) ?: 0
        val name = childSnapshot.child("name").getValue(String::class.java) ?: ""
        val imgUrl =
            childSnapshot.child("imgUrl").getValue(String::class.java) ?: ""
        val imgType =
            childSnapshot.child("imgType").getValue(String::class.java) ?: ""
        val recipe = Recipe(recipeId, name, imgUrl, imgType)
        favoriteRecipes.add(recipe)
    }

    fun redirectPreviousActivity(context: Context) {
        if (context is Activity) {
            context.finish()
        }
    }
}