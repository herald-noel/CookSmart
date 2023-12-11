package com.example.cooksmart.controller.recipeHistory

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.cooksmart.RecipeActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.listener.OnClickedFavoriteRecipeListener
import com.example.cooksmart.view.favoriteRecipe.FavoriteRecipeView
import com.example.cooksmart.view.recipeHistory.RecipeHistoryView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class RecipeHistoryController(private val recipeHistoryView: RecipeHistoryView) : Controller(), OnClickedFavoriteRecipeListener {
    val recipeHistory: ArrayList<Recipe> = ArrayList()

    fun retrieveRecipeHistory() {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeHistoryRef =
                databaseReference.child("users").child(it).child("recipeHistory")
            recipeHistoryRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Iterate through the snapshot to get recipeIds
                    for (childSnapshot in snapshot.children) {
                        getSnapShot(childSnapshot)
                    }
                    if (recipeHistory.size != 0) {
                        recipeHistoryView.showRecipeHistory(recipeHistory)
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
        val name = childSnapshot.child("title").getValue(String::class.java) ?: ""
        val imgUrl =
            childSnapshot.child("image").getValue(String::class.java) ?: ""
        val imgType =
            childSnapshot.child("imageType").getValue(String::class.java) ?: ""
        val recipe = Recipe(recipeId, name, imgUrl, imgType)
        Log.d("RECIPE", "$recipeId, $name, $imgUrl, $imgType")
        recipeHistory.add(recipe)
    }

    fun redirectPreviousActivity() {
        if (recipeHistoryView.context is Activity) {
            recipeHistoryView.context.finish()
        }
    }

    override fun onRecipeClicked(position: Int) {
        val recipeItem = recipeHistory[position]

        val intent = Intent(recipeHistoryView.context, RecipeActivity::class.java)
        intent.putExtra("recipeId", recipeItem.id)
        intent.putExtra("recipeName", recipeItem.title)
        intent.putExtra("recipeImageUrl", recipeItem.image)
        intent.putExtra("recipeImgType", recipeItem.imageType)
        if (recipeHistoryView.context is Activity) {
            recipeHistoryView.context.startActivity(intent)
        }
    }
}