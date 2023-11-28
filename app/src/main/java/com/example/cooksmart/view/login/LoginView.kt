package com.example.cooksmart.view.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cooksmart.R
import com.example.cooksmart.controller.login.LoginController
import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.base.CView

class LoginView(private val context: Context, viewGroup: ViewGroup?) : CView(), ILoginView {
    // MVC Variables
    override val model: LoginModel
    override val controller: LoginController
    override val view: View

    // EditText
    private var emailEditText: EditText
    private var passwordEditText: EditText

    // Button
    private var loginBtn: Button
    private var registerBtn: Button


    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_login, viewGroup)
        model = LoginModel()
        controller = LoginController(model, this)

        // Initialize EditText
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)

        // Initialize Button
        loginBtn = view.findViewById(R.id.loginBtn)
        registerBtn = view.findViewById(R.id.registerBtn)

        // Listeners
        loginBtnActionListener()
        registerBtnActionListener()
    }


    override fun getEmailText(): String {
        return emailEditText.text.toString()
    }

    override fun getPasswordText(): String {
        return passwordEditText.text.toString()
    }

    override fun getRootView(): View {
        return view
    }

    // Listeners
    private fun loginBtnActionListener() {
        loginBtn.setOnClickListener {
            controller.getLoginStatus(getEmailText(), getPasswordText())
        }
    }

    private fun registerBtnActionListener() {
        registerBtn.setOnClickListener {
            controller.redirectRegister(context)
        }
    }

    fun showSuccessToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}