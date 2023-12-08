package com.example.cooksmart.api.model.instructions

data class InstructionsResponseItem(
    val name: String,
    val steps: ArrayList<Step>
)
