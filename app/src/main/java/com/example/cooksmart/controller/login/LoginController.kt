package com.example.cooksmart.controller.login

import android.content.Context
import android.content.Intent
import android.util.Patterns
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
        val formValid = validateForm()
        if (formValid) {
            loginModel.login(email, password, this)
        }
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

    fun validateEmail(): String? {
        val emailText = loginView.getEmailText()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches() || emailText.isEmpty()) {
            return "Invalid Email Address"
        }
        return null
    }

    fun validatePassword(): String? {
        val passwordText = loginView.getPasswordText()
        if (passwordText.isEmpty()) {
            return "Please enter password."
        }
        return null
    }

    private fun validateForm(): Boolean {
        val emailContainer = loginView.getEmailContainer()
        val passwordContainer = loginView.getPasswordContainer()

        val emptyEmailError = (validateEmail() ?: "").isEmpty()
        val emptyPasswordError = (validatePassword() ?: "").isEmpty()

        val validEmail = emptyEmailError && emailContainer.error == null
        val validPassword = emptyPasswordError && passwordContainer.error == null

        return validEmail && validPassword
    }
}