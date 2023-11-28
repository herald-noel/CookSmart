package com.example.cooksmart.view.login

import com.example.cooksmart.view.base.IView

interface ILoginView: IView {
    fun getEmailText(): String
    fun getPasswordText(): String
}