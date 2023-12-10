package com.example.cooksmart.controller.favoriteRecipe

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.cooksmart.RecipeActivity
import com.example.cooksmart.adapter.FavoriteRecipeAdapter
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.listener.OnClickedFavoriteRecipeListener
import com.example.cooksmart.view.favoriteRecipe.FavoriteRecipeView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class FavoriteRecipeController(private val favoriteRecipeView: FavoriteRecipeView) : Controller(),
    OnClickedFavoriteRecipeListener {
    private var favoriteRecipes: ArrayList<Recipe> = ArrayList()
    var favoriteRecipeAdapter: FavoriteRecipeAdapter = FavoriteRecipeAdapter(favoriteRecipes, this)

    fun retrieveRecipeFavorites() {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeHistoryRef =
                databaseReference.child("users").child(it).child("recipeFavorites")
            recipeHistoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        getSnapShot(childSnapshot)
                    }
                    favoriteRecipeAdapter.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e(
                        "Recipe favorite",
                        "Error retrieving recipe favorite: ${error.message}"
                    )
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
        favoriteRecipes.add(recipe)
    }

    fun redirectPreviousActivity() {
        if (favoriteRecipeView.context is Activity) {
            favoriteRecipeView.context.finish()
        }
    }

    override fun onRecipeClicked(position: Int) {
        val recipeItem = favoriteRecipes[position]

        val intent = Intent(favoriteRecipeView.context, RecipeActivity::class.java)
        intent.putExtra("recipeId", recipeItem.id)
        intent.putExtra("recipeName", recipeItem.title)
        intent.putExtra("recipeImageUrl", recipeItem.image)
        intent.putExtra("recipeImgType", recipeItem.imageType)
        if (favoriteRecipeView.context is Activity) {
            favoriteRecipeView.context.startActivity(intent)
        }
    }

    override fun onRemove(position: Int) {
        val recipeId = favoriteRecipes[position].id
        removeFavoriteRecipeFirebase(recipeId, position)
    }

    private fun removeFavoriteRecipeFirebase(recipeId: Int, position: Int) {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeRef =
                databaseReference.child("users").child(it).child("recipeFavorites")
                    .child(recipeId.toString())
            recipeRef.removeValue().addOnCompleteListener {
                Log.d("REMOVE RECIPE", "$recipeId has been removed")
                favoriteRecipes.removeAt(position)
                favoriteRecipeAdapter.notifyItemRemoved(position)
            }
        }
    }
}