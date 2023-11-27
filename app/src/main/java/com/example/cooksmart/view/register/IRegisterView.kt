package com.example.cooksmart.view.register

import com.example.cooksmart.view.base.IView

interface IRegisterView: IView {
    fun getEmailText(): String
    fun getPasswordText(): String
    fun getConfirmPasswordText(): String
    fun getBirthdate(): String
}