package com.example.cooksmart.api.model.instructions

data class InstructionsResponse(
    val name: String,
    val steps: ArrayList<Step>
)
