package com.example.cooksmart.controller.register

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cooksmart.LoginActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.register.IRegisterCallback
import com.example.cooksmart.model.register.RegisterModel
import com.example.cooksmart.view.register.RegisterView

class RegisterController(
    private val registerModel: RegisterModel,
    private val registerView: RegisterView
) : Controller(),
    IRegisterController,
    IRegisterCallback {

    override fun getRegisterMessage(email: String, password: String) {
        registerModel.createUser(email, password, this)
    }

    fun redirectLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onRegistration(message: String) {
        registerView.showToast(message)
        println(message)
    }
}