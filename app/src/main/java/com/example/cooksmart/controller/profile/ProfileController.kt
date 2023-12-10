package com.example.cooksmart.controller.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cooksmart.FavoriteRecipeActivity
import com.example.cooksmart.HomeActivity
import com.example.cooksmart.LoginActivity
import com.example.cooksmart.ProfileActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.model.profile.IProfileCallback
import com.example.cooksmart.model.profile.ProfileModel;
import com.example.cooksmart.model.recipe.RecipeModel
import com.example.cooksmart.view.profile.ProfileView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ProfileController(
    private val profileModel: ProfileModel,
    private val profileView: ProfileView
) : Controller(), IProfileController {
    val favoriteRecipes: ArrayList<Recipe> = ArrayList()

    val mAuth = FirebaseAuth.getInstance()
    val currentUserId = FirebaseAuth.getInstance().currentUser
    val uID = currentUserId?.getUid()
    val reference = FirebaseDatabase.getInstance().getReference("users").child(uID.orEmpty())

    private val callback = object : IProfileCallback {
        override fun onProfileReceived(profile: ProfileModel) {
            profileView.updateProfile(profileModel)
        }

        override fun onProfileError(error: String) {
            TODO("Not yet implemented")
        }
    }

    override fun displayPastRecipe() {
    }

    override fun FavoriteRecipe() {
    }

    override fun displayProfileDetails() {
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.children
                for (userSnapshot in data) {
                    when (userSnapshot.key) {
                        "email" -> profileModel.email = userSnapshot.getValue().toString()
                        "birthdate" -> profileModel.birthdate = userSnapshot.getValue().toString()
                        "pastRecipes" -> {
                            for (recipeSnapshot in userSnapshot.child("pastRecipes").children) {
                                val recipe = recipeSnapshot.getValue(RecipeModel::class.java)
                                recipe?.let {
                                    profileModel.pastRecipes.addRecipeToHistory(it)
                                }
                            }
                        }

                        "favoriteRecipes" -> {
                            for (recipeSnapshot in userSnapshot.child("favoriteRecipes").children) {
                                val recipe = recipeSnapshot.getValue(RecipeModel::class.java)
                                recipe?.let {
                                    profileModel.favoriteRecipes.addRecipeToList(it)
                                }
                            }
                        }
                    }
                }
                callback.onProfileReceived(profileModel)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
            }
        })
    }

    fun redirectToHome(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }

    fun redirectToProfile(context: Context) {
        val intent = Intent(context, ProfileActivity::class.java)
        context.startActivity(intent)
    }

    fun logout(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        if (context is Activity) {
            context.finishAffinity()
        }
        mAuth.signOut()
    }

    fun redirectFavoriteRecipes(context: Context) {
        val intent = Intent(context, FavoriteRecipeActivity::class.java)
        context.startActivity(intent)
        if (context is Activity) {
            context.finish()
        }
    }

    fun showFavoriteRecipesThumbnail() {
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.uid
        val databaseReference = Firebase.database.reference

        uid?.let {
            val recipeHistoryRef =
                databaseReference.child("users").child(it).child("recipeFavorites")
            recipeHistoryRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Iterate through the snapshot to get recipeIds
                    var ctr = 0
                    for (childSnapshot in snapshot.children) {
                        if (ctr > 2) {
                            break;
                        }
                        ctr++
                        getSnapShot(childSnapshot)
                    }
                    if (favoriteRecipes.size != 0) {
                        profileView.showFavoriteRecipes(favoriteRecipes)
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
        favoriteRecipes.add(recipe)
    }
}