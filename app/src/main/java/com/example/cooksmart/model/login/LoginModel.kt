package com.example.cooksmart.model.login

import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.login.utils.LoginFirebase
import com.example.cooksmart.model.login.utils.Result


class LoginModel : Model(), ILoginModel {
    private val loginFirebase = LoginFirebase()

    // Success = 1
    // Success = 0
    override fun login(email: String, password: String, callback: ILoginCallback) {
        loginFirebase.login(email, password) { result ->
            when (result) {
                is Result.Success -> {
                    callback.onLogin(true)
                }

                is Result.Error -> {
                    callback.onLogin(false)
                    println(result.exception.message)
                }
            }

        }
    }
}