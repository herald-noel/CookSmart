package com.example.cooksmart.api.model.instructions

data class Step(
    val equipment: ArrayList<Equipment>,
    val ingredients: ArrayList<Ingredient>,
    val number: Int,
    val step: String,
    val length: Length
)
