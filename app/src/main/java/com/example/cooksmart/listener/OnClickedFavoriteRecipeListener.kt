package com.example.cooksmart.listener

interface OnClickedFavoriteRecipeListener {
    fun onRecipeClicked(position: Int)
    fun onRemove(position: Int)
}