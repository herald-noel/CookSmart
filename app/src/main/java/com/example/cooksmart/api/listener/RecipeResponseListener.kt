package com.example.cooksmart.api.listener

import com.example.cooksmart.api.model.RecipeApiResponse

interface RecipeResponseListener {
    fun didFetch(response: RecipeApiResponse, message: String)
    fun didError(message: String)
}