package com.example.cooksmart.listener

import com.example.cooksmart.data.Ingredient

interface OnIngredientChangedListener {
    fun onRemoveIngredient(ingredients: ArrayList<Ingredient>)
}