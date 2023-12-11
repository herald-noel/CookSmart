package com.example.cooksmart.view.recipeHistory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.adapter.RecipeHistoryAdapter
import com.example.cooksmart.controller.recipeHistory.RecipeHistoryController

import com.example.cooksmart.data.Recipe
import com.example.cooksmart.model.pastrecipe.PastRecipeModel
import com.example.cooksmart.view.base.CView

class RecipeHistoryView(val context: Context, viewGroup: ViewGroup?) : CView() {

    override val view: View
    override var model: PastRecipeModel
    override val controller: RecipeHistoryController

    private val recipeHistoryRecyclerView: RecyclerView
    private val backBtn: ImageButton


    init {
        model = PastRecipeModel()
        controller = RecipeHistoryController(this)
        view = LayoutInflater.from(context).inflate(R.layout.activity_recipe_history, viewGroup)
        recipeHistoryRecyclerView = view.findViewById(R.id.recipeHistoryRecyclerView)

        backBtn = view.findViewById(R.id.backBtn)
        initRecipeHistory()
        backButtonListener()
    }

    override fun getRootView(): View {
        return view
    }

    fun showRecipeHistory(recipeHistory: ArrayList<Recipe>) {
        recipeHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        recipeHistoryRecyclerView.adapter = RecipeHistoryAdapter(recipeHistory, controller)
    }

    private fun initRecipeHistory() {
        controller.retrieveRecipeHistory()
    }

    private fun backButtonListener() {
       backBtn.setOnClickListener{
           controller.redirectPreviousActivity()
       }
    }
}