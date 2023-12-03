package com.example.cooksmart.controller.login

import android.content.Context
import com.example.cooksmart.controller.base.IController

interface ILoginController: IController {
    fun getLoginStatus(email: String, password: String)
}