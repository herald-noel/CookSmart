package com.example.cooksmart.controller.login

import com.example.cooksmart.controller.IController

interface ILoginController: IController {
    fun verifyUser()
    fun getLoginStatus()
}