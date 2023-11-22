package com.example.cooksmart.view.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.cooksmart.R
import com.example.cooksmart.controller.register.RegisterController
import com.example.cooksmart.model.register.RegisterModel

class RegisterView(private val context: Context, viewGroup: ViewGroup?) : IRegisterView {
    private val registerView: View
    private val registerModel: RegisterModel
    private val registerController: RegisterController

    init {
        registerView = LayoutInflater.from(context).inflate(R.layout.activity_register, viewGroup)
        registerModel = RegisterModel()
        registerController = RegisterController(registerModel, this)

        val loginBtn: Button = registerView.findViewById(R.id.loginRegisterBtn)
        loginBtnActionListener(loginBtn)
    }

    private val usernameEditText: EditText =
        registerView.findViewById(R.id.usernameRegisterEditText)
    private val emailEditText: EditText = registerView.findViewById(R.id.emailRegisterEditText)
    private val passwordEditText: EditText =
        registerView.findViewById(R.id.passwordRegisterEditText)
    private val confirmPasswordEditText: EditText =
        registerView.findViewById(R.id.confirmPasswordRegisterEditText)
    private val birthDateEditText: EditText =
        registerView.findViewById(R.id.birthdateRegisterEditText)

    override fun getUsernameText(): String {
        return usernameEditText.toString()
    }

    override fun getEmailText(): String {
        return emailEditText.toString()
    }

    override fun getPasswordText(): String {
        return passwordEditText.toString()
    }

    override fun getConfirmPasswordText(): String {
        return confirmPasswordEditText.toString()
    }

    override fun getBirthdate(): String {
        return birthDateEditText.toString()
    }

    override fun getRootView(): View {
        return registerView
    }

    fun loginBtnActionListener(loginBtn: Button) {
        loginBtn.setOnClickListener {
            registerController.redirectLogin(context)
        }
    }

}