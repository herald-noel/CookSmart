package com.example.cooksmart.view.login

import com.example.cooksmart.view.IView

interface ILoginView: IView {
    fun getUsernameTxt(): String
    fun getPasswordTxt(): String
}