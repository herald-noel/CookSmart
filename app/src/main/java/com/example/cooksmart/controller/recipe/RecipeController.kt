package com.example.cooksmart.controller.recipe

import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.recipe.RecipeModel
import com.example.recipeview.view.IRecipeView

class RecipeController(private val recipeModel: RecipeModel,
private val recipeView: IRecipeView
) : Controller(), IRecipeController{


}