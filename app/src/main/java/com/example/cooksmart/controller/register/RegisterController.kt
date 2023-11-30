package com.example.cooksmart.controller.register

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import com.example.cooksmart.LoginActivity
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.register.IRegisterCallback
import com.example.cooksmart.model.register.RegisterModel
import com.example.cooksmart.view.register.RegisterView

class RegisterController(
    private val registerModel: RegisterModel, private val registerView: RegisterView
) : Controller(), IRegisterController, IRegisterCallback {

    override fun getRegisterMessage(email: String, password: String, birthdate: String) {
        val isFormValid = validateForm()
        if (isFormValid) {
            registerModel.createUser(email, password, birthdate, this)
        } else {
            registerView.showToast("Uh-oh! Invalid details. Please check your entries and try again.")
        }
    }

    fun redirectLogin(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onRegistration(message: String) {
        if (message == "EMAIL EXIST") {
            registerView.showEmailExistError()
        } else {
            registerView.showToast(message)
        }
    }

    fun validateEmail(): String? {
        val emailText = registerView.getEmailText()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches() || emailText.isEmpty()) {
            return "Invalid Email Address"
        }
        return null
    }

    fun validatePassword(): String? {
        val passwordText = registerView.getPasswordText()
        if (passwordText.length < 8) {
            return "Minimum of 8 characters"
        }
        return null
    }

    fun validateConfirmPassword(): String? {
        val passwordText = registerView.getPasswordText()
        val confirmPasswordText = registerView.getConfirmPasswordText()
        if (passwordText != confirmPasswordText) {
            return "Password does not match"
        }
        if (confirmPasswordText.isEmpty()) {
            return "Please re-enter password"
        }
        return null
    }

    fun validateDatePicker(): String? {
        val datePicker = registerView.getDatePicker()
        if (datePicker.selection == null) {
            return "Date not selected"
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

        Log.d("FOrm", "$emptyEmailError $emptyPasswordError $emptyConfirmPassword $emptyDateError")

        val validEmail = emptyEmailError && emailContainer.error == null
        val validPassword = emptyPasswordError && passwordContainer.error == null
        val validConfirmPassword =
            emptyConfirmPassword && confirmPasswordContainer.error == null
        val validDate = emptyDateError && datePickerContainer.error == null

        Log.d("FOrm", "$validEmail $validPassword $validConfirmPassword $validDate")
        Log.d("email", "${!emptyEmailError} ${emailContainer.error == null}")

        return validEmail && validPassword && validConfirmPassword && validDate
    }
}