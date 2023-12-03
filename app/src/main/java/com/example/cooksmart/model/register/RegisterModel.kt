package com.example.cooksmart.model.register

import android.util.Log
import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.login.data.LoggedInUser
import com.example.cooksmart.model.register.data.User
import com.example.cooksmart.model.register.utils.SignUpMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterModel : Model(), IRegisterModel {
    private val mAuth = FirebaseAuth.getInstance()

    override fun createUser(
        email: String,
        password: String,
        birthdate: String,
        callback: IRegisterCallback
    ) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = task.result.user
                    val newUser = User(email, birthdate)

                    user?.let { writeUserDetails(newUser, it.uid) }
                    callback.onRegistration(SignUpMessage.success)
                } else {
                    val ex = task.exception
                    if (ex is FirebaseAuthUserCollisionException) {
                        callback.onRegistration(SignUpMessage.emailExist)
                    } else {
                        callback.onRegistration("Error: ${ex?.message}")
                    }
                }
            }
    }

    // Write additional user details in realtime database
    private fun writeUserDetails(userDetails: User, uid: String) {
        Log.d("User id", uid)

        val databaseReference = Firebase.database.reference
        databaseReference.child("users").child(uid).setValue(userDetails)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Write success", "User details written successfully.")
                } else {
                    Log.e("Write error", "Failed to write user details: ${task.exception}")
                }
            }
    }
}