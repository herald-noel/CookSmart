package com.example.cooksmart.controller.register

import com.example.cooksmart.controller.IController

interface IRegisterController: IController {
    fun verifyRegister()
    fun getRegisterStatus()
    fun getRegisterMessage()
}