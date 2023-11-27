package com.example.cooksmart.view.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.cooksmart.R
import com.example.cooksmart.controller.register.RegisterController
import com.example.cooksmart.model.register.RegisterModel
import com.example.cooksmart.view.base.CView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterView(private val context: Context, viewGroup: ViewGroup?) : CView(), IRegisterView {
    override val view: View
    override val model: RegisterModel
    override val controller: RegisterController

    // EditText
    private var emailEditText: EditText
    private var passwordEditText: EditText
    private var confirmPasswordEditText: EditText
    private var birthDateEditText: EditText
    private var dateText: TextInputEditText


    // Button
    private var loginBtn: Button
    private var registerBtn: Button

    private lateinit var dateSelected: String

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_register, viewGroup)
        model = RegisterModel()
        controller = RegisterController(model, this)

        // Initialize EditText
        emailEditText = view.findViewById(R.id.emailRegisterEditText)
        passwordEditText =
            view.findViewById(R.id.passwordRegisterEditText)
        confirmPasswordEditText =
            view.findViewById(R.id.confirmPasswordRegisterEditText)
        birthDateEditText =
            view.findViewById(R.id.birthdateRegisterEditText)
        dateText = getRootView().findViewById(R.id.birthdateRegisterEditText)

        // Initialize Button
        loginBtn = view.findViewById(R.id.loginRegisterBtn)
        registerBtn = view.findViewById(R.id.signUpBtn)

        loginBtnActionListener()
        registerBtnActionListener()
    }

    override fun getEmailText(): String {
        return emailEditText.text.toString()
    }

    override fun getPasswordText(): String {
        return passwordEditText.text.toString()
    }

    override fun getConfirmPasswordText(): String {
        return confirmPasswordEditText.toString()
    }

    override fun getBirthdate(): String {
        return dateSelected
    }

    override fun getRootView(): View {
        return view
    }

    private fun loginBtnActionListener() {
        loginBtn.setOnClickListener {
            controller.redirectLogin(context)
        }
    }

    private fun registerBtnActionListener() {
        registerBtn.setOnClickListener {
            controller.getRegisterMessage(getEmailText(), getPasswordText(), getBirthdate())
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun showDatePickerDialog() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            // Convert the selected date to a formatted string if needed
            dateSelected =
                SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(selectedDate))

            // Update the TextInputEditText with the selected date
            birthDateEditText.setText(dateSelected)
        }

        datePicker.show(
            (context as FragmentActivity).supportFragmentManager, datePicker.toString()
        )
    }

}