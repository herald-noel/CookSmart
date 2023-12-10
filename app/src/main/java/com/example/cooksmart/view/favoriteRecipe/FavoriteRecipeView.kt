package com.example.cooksmart.view.favoriteRecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.adapter.FavoriteRecipeAdapter
import com.example.cooksmart.controller.favoriteRecipe.FavoriteRecipeController

import com.example.cooksmart.data.Recipe
import com.example.cooksmart.model.favoriterecipe.FavoriteRecipeModel
import com.example.cooksmart.view.base.CView

class FavoriteRecipeView(val context: Context, viewGroup: ViewGroup?) : CView() {

    override val view: View
    override var model: FavoriteRecipeModel
    override val controller: FavoriteRecipeController

    private val favoriteRecipeRecyclerView: RecyclerView
    private val backBtn: ImageButton
    private var favoriteRecipes: ArrayList<Recipe> = ArrayList()


    init {
        model = FavoriteRecipeModel()
        controller = FavoriteRecipeController(this)
        view = LayoutInflater.from(context).inflate(R.layout.activity_favorite_recipe, viewGroup)
        favoriteRecipeRecyclerView = view.findViewById(R.id.favoriteRecipeRecyclerView)

        backBtn = view.findViewById(R.id.favoriteBackBtn)
        initFavoriteRecipes()
        backButtonListener()
    }

    override fun getRootView(): View {
        return view
    }

    fun getFavoriteRecipeList(): ArrayList<Recipe> {
        return favoriteRecipes
    }

    fun getAdapter() {

    }

    fun showFavoriteRecipes(favoriteRecipes: ArrayList<Recipe>) {
        this.favoriteRecipes = favoriteRecipes
        favoriteRecipeRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteRecipeRecyclerView.adapter = FavoriteRecipeAdapter(favoriteRecipes, controller)
    }

    private fun initFavoriteRecipes() {
        controller.retrieveRecipeFavorites()
    }

    private fun backButtonListener() {
        backBtn.setOnClickListener {
            controller.redirectPreviousActivity()
        }
    }
}