package com.example.cooksmart.model.register

import com.example.cooksmart.model.base.Model
import com.google.firebase.auth.FirebaseAuth

class RegisterModel : Model(), IRegisterModel {
    private val mAuth = FirebaseAuth.getInstance()

    override fun createUser(
        email: String,
        password: String,
        callback: IRegisterCallback
    ) {
        val success = "Registration success."
        val error = "Registration unsuccessful."

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.onRegistration(success)
                } else {
                    callback.onRegistration(error)
                }
            }
    }
}