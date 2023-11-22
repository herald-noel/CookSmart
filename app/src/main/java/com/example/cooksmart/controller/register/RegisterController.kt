package com.example.cooksmart.controller.register

import android.content.Context
import android.content.Intent
import com.example.cooksmart.LoginActivity
import com.example.cooksmart.model.register.RegisterModel
import com.example.cooksmart.view.register.RegisterView

class RegisterController(
    private val registerModel: RegisterModel,
    registerView: RegisterView
) : IRegisterController {
    override fun verifyRegister() {
        TODO("Not yet implemented")
    }

    override fun getRegisterStatus() {
        TODO("Not yet implemented")
    }

    override fun getRegisterMessage() {
        TODO("Not yet implemented")
    }

    fun redirectLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}