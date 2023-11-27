package com.example.cooksmart.controller.register

import com.example.cooksmart.controller.base.IController

interface IRegisterController: IController {
    fun getRegisterMessage(email: String, password: String)
}