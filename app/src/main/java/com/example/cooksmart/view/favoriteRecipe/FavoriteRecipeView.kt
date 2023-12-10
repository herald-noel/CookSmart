package com.example.cooksmart.view.favoriteRecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.controller.favoriteRecipe.FavoriteRecipeController
import com.example.cooksmart.model.favoriterecipe.FavoriteRecipeModel
import com.example.cooksmart.view.base.CView

class FavoriteRecipeView(
    val context: Context,
    viewGroup: ViewGroup?
) : CView() {

    override val view: View
    override var model: FavoriteRecipeModel = FavoriteRecipeModel()
    override val controller: FavoriteRecipeController = FavoriteRecipeController(this)

    private val favoriteRecipeRecyclerView: RecyclerView
    private val backBtn: ImageButton

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_favorite_recipe, viewGroup)
        backBtn = view.findViewById(R.id.favoriteBackBtn)
        backButtonListener()

        controller.retrieveRecipeFavorites()

        favoriteRecipeRecyclerView = view.findViewById(R.id.favoriteRecipeRecyclerView)
        favoriteRecipeRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteRecipeRecyclerView.adapter = controller.favoriteRecipeAdapter
    }

    override fun getRootView(): View {
        return view
    }

    private fun backButtonListener() {
        backBtn.setOnClickListener {
            controller.redirectPreviousActivity()
        }
    }
}