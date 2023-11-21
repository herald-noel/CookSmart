package com.example.cooksmart.controller.login.login

import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.login.LoginView

class LoginController(
    private val loginModel: LoginModel,
    loginView: LoginView
) : ILoginController {

    override fun verifyUser() {
        return loginModel.getUserDetails()
    }

    override fun getLoginStatus() {
        TODO("Not yet implemented")
    }

}