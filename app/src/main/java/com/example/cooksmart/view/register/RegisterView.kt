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
import com.example.cooksmart.view.base.CView

class RegisterView(private val context: Context, viewGroup: ViewGroup?) : CView(),IRegisterView {
    override val view: View
    override val model: RegisterModel
    override val controller: RegisterController

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_register, viewGroup)
        model = RegisterModel()
        controller = RegisterController(model, this)

        val loginBtn: Button = view.findViewById(R.id.loginRegisterBtn)
        loginBtnActionListener(loginBtn)
    }

    private val usernameEditText: EditText =
        view.findViewById(R.id.usernameRegisterEditText)
    private val emailEditText: EditText = view.findViewById(R.id.emailRegisterEditText)
    private val passwordEditText: EditText =
        view.findViewById(R.id.passwordRegisterEditText)
    private val confirmPasswordEditText: EditText =
        view.findViewById(R.id.confirmPasswordRegisterEditText)
    private val birthDateEditText: EditText =
        view.findViewById(R.id.birthdateRegisterEditText)

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
        return view
    }

    private fun loginBtnActionListener(loginBtn: Button) {
        loginBtn.setOnClickListener {
            controller.redirectLogin(context)
        }
    }

}