package com.example.cooksmart.view.register

import com.example.cooksmart.view.IView

interface IRegisterView: IView {
    fun getUsernameText(): String
    fun getEmailText(): String
    fun getPasswordText(): String
    fun getConfirmPasswordText(): String
    fun getBirthdate(): String
}