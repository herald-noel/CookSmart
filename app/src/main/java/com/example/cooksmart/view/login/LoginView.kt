package com.example.cooksmart.view.login

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.cooksmart.R
import com.example.cooksmart.controller.login.LoginController
import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.base.CView

class LoginView(private val context: Context, viewGroup: ViewGroup?) : CView(), ILoginView {
    // MVC Variables
    override val model: LoginModel
    override val controller: LoginController
    override val view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_login, viewGroup)
        model = LoginModel()
        controller = LoginController(model, this)

        // Button
        val loginButton: Button = view.findViewById(R.id.loginBtn)
        val registerButton: Button = view.findViewById(R.id.registerBtn)
        loginBtnActionListener(loginButton)
        registerBtnActionListener(registerButton)
    }

    private val emailEditText: EditText = view.findViewById(R.id.emailEditText)
    private val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)

    override fun getEmailTxt(): String {
        return emailEditText.toString()
    }

    override fun getPasswordTxt(): String {
        return passwordEditText.toString()
    }

    override fun getRootView(): View {
        return view
    }

    // Listeners
    private fun loginBtnActionListener(loginButton: Button) {
        loginButton.setOnClickListener {
            Log.d("hello", "test")
            // loginController.verifyUser()
        }
    }

    private fun registerBtnActionListener(registerBtn: Button) {
        registerBtn.setOnClickListener {
           controller.redirectRegister(context)
        }
    }
}