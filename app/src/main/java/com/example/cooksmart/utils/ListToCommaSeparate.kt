package com.example.cooksmart.utils

import com.example.cooksmart.Ingredient


class ListToCommaSeparate {
    companion object {
        fun convertToString(list: MutableList<Ingredient>): String {
            return list.joinToString(separator = ",") {it.name}
        }
    }
}