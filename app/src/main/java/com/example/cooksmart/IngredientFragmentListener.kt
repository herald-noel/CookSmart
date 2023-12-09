package com.example.cooksmart

import com.example.cooksmart.data.Ingredient

interface IngredientFragmentListener {
    fun onIngredientChange(updatedIngredients: MutableList<Ingredient>)
}