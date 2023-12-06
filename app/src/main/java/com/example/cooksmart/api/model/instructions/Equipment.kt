package com.example.cooksmart.api.model.instructions

data class Equipment(
    val id: Int,
    val image: String,
    val name: String,
    val temperature: Temperature
)
