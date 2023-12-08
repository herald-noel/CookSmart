package com.example.cooksmart.controller.recipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cooksmart.api.RequestManager
import com.example.cooksmart.api.listener.InstructionsListener
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.view.recipe.RecipeView

class RecipeController(
    private val recipeView: RecipeView
): Controller() {

    fun managerGetRecipeDirection (id: Int) {
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