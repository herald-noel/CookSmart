package com.example.cooksmart.controller.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Patterns
import com.example.cooksmart.HomeActivity
import com.example.cooksmart.LoginActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.register.IRegisterCallback
import com.example.cooksmart.model.register.RegisterModel
import com.example.cooksmart.model.register.utils.SignUpMessage
import com.example.cooksmart.view.register.RegisterView

class RegisterController(
    private val registerModel: RegisterModel, private val registerView: RegisterView
) : Controller(), IRegisterController, IRegisterCallback {

    override fun getRegisterMessage(email: String, password: String, birthdate: String) {
        val isFormValid = validateForm()
        if (isFormValid) {
            registerModel.createUser(email, password, birthdate, this)
        } else {
            registerView.showToast(SignUpMessage.invalidCredentialError)
        }
    }

    fun redirectLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onRegistration(message: String) {
        when (message) {
            SignUpMessage.success -> {
                registerView.showToast(SignUpMessage.success)

                val context = registerView.getContext()
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)

                // if context is not an instance of Activity or if it's null, the finish()
                // method won't be called, preventing potential crashes.
                (context as? Activity)?.finish()
            }

            SignUpMessage.emailExist -> {
                registerView.showEmailExistError()
            }

            else -> {
                registerView.showToast(message)
            }
        }
    }

    fun validateEmail(): String? {
        val emailText = registerView.getEmailText()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches() || emailText.isEmpty()) {
            return SignUpMessage.invalidEmail
        }
        return null
    }

    fun validatePassword(): String? {
        val passwordText = registerView.getPasswordText()
        if (passwordText.length < 8) {
            return SignUpMessage.passLenError
        }
        return null
    }

    fun validateConfirmPassword(): String? {
        val passwordText = registerView.getPasswordText()
        val confirmPasswordText = registerView.getConfirmPasswordText()
        if (passwordText != confirmPasswordText) {
            return SignUpMessage.passNotMatchError
        }
        if (confirmPasswordText.isEmpty()) {
            return SignUpMessage.passEmptyError
        }
        return null
    }

    fun validateDatePicker(): String? {
        val datePicker = registerView.getDatePicker()
        if (datePicker.selection == null) {
            return SignUpMessage.dateNullError
        }
        return null
    }

    private fun validateForm(): Boolean {
        val emailContainer = registerView.getEmailContainer()
        val passwordContainer = registerView.getPasswordContainer()
        val confirmPasswordContainer = registerView.getConfirmPasswordContainer()
        val datePickerContainer = registerView.getDatePickerContainer()

        val emptyEmailError = (validateEmail() ?: "").isEmpty()
        val emptyPasswordError = (validatePassword() ?: "").isEmpty()
        val emptyConfirmPassword = (validateConfirmPassword() ?: "").isEmpty()
        val emptyDateError = (validateDatePicker() ?: "").isEmpty()

        val validEmail = emptyEmailError && emailContainer.error == null
        val validPassword = emptyPasswordError && passwordContainer.error == null
        val validConfirmPassword = emptyConfirmPassword && confirmPasswordContainer.error == null
        val validDate = emptyDateError && datePickerContainer.error == null

        return validEmail && validPassword && validConfirmPassword && validDate
    }
}