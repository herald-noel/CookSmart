package com.example.cooksmart.api.model

data class MissedIngredient(
    val aisle: String,
    val amount: Double,
    val extendedName: String,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val metaInformation: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val originalString: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String,
)


