package com.example.cooksmart.model.login

import com.example.cooksmart.model.base.IModel

interface ILoginModel: IModel {
    fun login(email: String, password: String, callback: ILoginCallback)
}