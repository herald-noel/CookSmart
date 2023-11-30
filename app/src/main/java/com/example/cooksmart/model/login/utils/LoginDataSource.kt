package com.example.cooksmart.model.login.utils

import android.util.Log
import com.example.cooksmart.model.login.data.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException

class LoginDataSource {
    private val mAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, callback: (Result<LoggedInUser>) -> Unit) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("SIGN IN", "signInWithEmail:success")
                val loggedInUser = LoggedInUser(email)
                callback(Result.Success(loggedInUser))
            } else {
                Log.d("SIGN IN ERROR", "signInWithEmail:failure", task.exception)
                val error = Result.Error(IOException("Incorrect credentials", task.exception))
                callback(error)
            }
        }
    }

    fun logout() {
        mAuth.signOut()
    }
}