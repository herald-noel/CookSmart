package com.example.cooksmart.controller.login

import android.content.Context
import android.content.Intent
import com.example.cooksmart.RegisterActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.login.ILoginCallback
import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.login.LoginView

class LoginController(
    private val loginModel: LoginModel,
    private val loginView: LoginView
) : Controller(), ILoginController, ILoginCallback {

    override fun getLoginStatus(email: String, password: String) {
        // TODO validate editText first
        loginModel.login(email, password, this)
    }

    fun redirectRegister(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
    }

    override fun onLogin(status: Boolean) {
        if (status) {
           loginView.showSuccessToast("Login successfully")
            // TODO redirect to main
        } else {
            loginView.showErrorToast("Please try again.")
            // TODO make error clear
        }
    }
}