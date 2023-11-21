package com.example.cooksmart.view.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.cooksmart.R
import com.example.cooksmart.controller.login.login.LoginController
import com.example.cooksmart.model.login.LoginModel

class LoginView(context: Context, viewGroup: ViewGroup) : ILoginView {
    // MVC Variables
    private val loginView: View
    private val loginController: LoginController
    private val loginModel: LoginModel

    init {
        loginView = LayoutInflater.from(context).inflate(R.layout.activity_login, viewGroup)
        loginModel = LoginModel()
        loginController = LoginController(loginModel, this)

        loginBtnActionListener()
    }

    private val usernameEditText: EditText = loginView.findViewById(R.id.usernameEditText)
    private val passwordEditText: EditText = loginView.findViewById(R.id.passwordEditText)
    private val loginButton: Button = loginView.findViewById(R.id.loginBtn)

    override fun getUsernameTxt(): String {
        return usernameEditText.toString()
    }

    override fun getPasswordTxt(): String {
        return passwordEditText.toString()
    }

    // Listeners
    private fun loginBtnActionListener() {
        loginButton.setOnClickListener {
            loginController.verifyUser()
        }
    }


}