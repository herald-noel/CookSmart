package com.example.cooksmart.api.listener

import com.example.cooksmart.api.model.instructions.InstructionsResponse

interface InstructionsListener {
    fun didFetch(response: InstructionsResponse, message: String)
    fun didError(message: String)
}