package com.example.cooksmart.model.login

/**
 * status = True, Login successful
 * else Login unsuccessful
 */

interface ILoginCallback {
    fun onLogin(status: Boolean)
}