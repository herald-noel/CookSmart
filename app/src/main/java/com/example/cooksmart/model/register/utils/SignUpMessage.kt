package com.example.cooksmart.model.register.utils

class SignUpMessage {
    companion object {
        const val success = "Registration success."
        const val emailExist = "Email already exist."
        const val invalidCredentialError =
            "Uh-oh! Invalid details. Please check your entries and try again."

        const val invalidEmail = "Invalid Email Address"
        const val passLenError = "Minimum of 8 characters"
        const val passNotMatchError = "Password does not match"
        const val passEmptyError = "Please re-enter password"
        const val dateNullError = "Date not selected"
    }
}