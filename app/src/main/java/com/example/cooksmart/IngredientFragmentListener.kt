package com.example.cooksmart

interface IngredientFragmentListener {
    fun onIngredientChange(updatedIngredients: MutableList<Ingredient>)
}