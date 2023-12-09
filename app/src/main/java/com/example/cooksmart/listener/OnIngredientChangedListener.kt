package com.example.cooksmart.listener

import com.example.cooksmart.Ingredient

interface OnIngredientChangedListener {
    fun onRemoveIngredient(ingredients: ArrayList<Ingredient>)
}