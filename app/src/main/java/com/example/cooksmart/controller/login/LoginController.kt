package com.example.cooksmart.controller.login

import android.content.Context
import android.content.Intent
import com.example.cooksmart.RegisterActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.login.LoginView

class LoginController(
    private val loginModel: LoginModel,
    loginView: LoginView
) : Controller(), ILoginController {

    override fun verifyUser() {
        return loginModel.getUserDetails()
    }

    override fun getLoginStatus() {
        TODO("Not yet implemented")
    }

    fun redirectRegister(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
    }

}