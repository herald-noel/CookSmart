package com.example.cooksmart.model.register

interface IRegisterModel {
    fun createUser(email: String, password: String, birthdate: String, callback: IRegisterCallback)
}