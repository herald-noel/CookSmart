package com.example.cooksmart.controller.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Patterns
import com.example.cooksmart.HomeActivity
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
        val formValid = validateForm()
        if (formValid) {
            loginModel.login(email, password, this)
        } else {
            loginView.showErrorToast("Incorrect credentials")
        }
    }

    fun redirectRegister() {
        val intent = Intent(loginView.getContext(), RegisterActivity::class.java)
        loginView.getContext().startActivity(intent)
    }

    private fun redirectMain(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)

        // if context is not an instance of Activity or if it's null, the finish()
        // method won't be called, preventing potential crashes.
        (context as? Activity)?.finish()
    }

    override fun onLogin(status: Boolean) {
        if (status) {
            loginView.showSuccessToast("Login successfully")
            redirectMain(loginView.getContext())
        } else {
            loginView.showErrorToast("Incorrect credentials. Please try again.")
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