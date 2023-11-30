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
import com.google.android.material.textfield.TextInputLayout
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

    // EditText Container
    private var emailContainer: TextInputLayout
    private var passwordContainer: TextInputLayout
    private var confirmPasswordContainer: TextInputLayout
    private var datePickerContainer: TextInputLayout

    // Button
    private var loginBtn: Button
    private var registerBtn: Button

    private var dateSelected: String
    private var datePicker: MaterialDatePicker<Long>

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_register, viewGroup)
        model = RegisterModel()
        controller = RegisterController(model, this)

        dateSelected = ""

        // Initialize EditText
        emailEditText = view.findViewById(R.id.emailRegisterEditText)
        passwordEditText = view.findViewById(R.id.passwordRegisterEditText)
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordRegisterEditText)
        birthDateEditText = view.findViewById(R.id.birthdateRegisterEditText)
        dateText = getRootView().findViewById(R.id.birthdateRegisterEditText)

        // Initialize Button
        loginBtn = view.findViewById(R.id.loginRegisterBtn)
        registerBtn = view.findViewById(R.id.signUpBtn)

        // Initialize container
        emailContainer = view.findViewById(R.id.emailContainer)
        passwordContainer = view.findViewById(R.id.passwordContainer)
        confirmPasswordContainer = view.findViewById(R.id.confirmPasswordContainer)
        datePickerContainer = view.findViewById(R.id.datePickerContainer)

        datePicker = MaterialDatePicker.Builder.datePicker().build()

        loginBtnActionListener()
        registerBtnActionListener()

        emailFocusListener()
        passwordFocusListener()
        confirmPasswordFocusListener()
        datePickerConfirmBtnActionListener()
        datePickerCancelBtnActionListener()
    }

    // Getters
    override fun getEmailText(): String {
        return emailEditText.text.toString()
    }

    override fun getPasswordText(): String {
        return passwordEditText.text.toString()
    }

    override fun getConfirmPasswordText(): String {
        return confirmPasswordEditText.text.toString()
    }

    override fun getBirthdate(): String {
        return dateSelected
    }

    override fun getRootView(): View {
        return view
    }

    fun getDatePicker(): MaterialDatePicker<Long> {
        return datePicker
    }

    fun getEmailContainer(): TextInputLayout {
        return emailContainer
    }

    fun getPasswordContainer(): TextInputLayout {
        return passwordContainer
    }

    fun getConfirmPasswordContainer(): TextInputLayout {
        return confirmPasswordContainer
    }

    fun getDatePickerContainer(): TextInputLayout {
        return datePickerContainer
    }

    fun showDatePickerDialog() {
        val datePickerTag = "BirthdateTag"
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val existingDatePicker = fragmentManager.findFragmentByTag(datePickerTag)

        if (existingDatePicker == null) {
            datePicker.show(fragmentManager, datePickerTag)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showEmailExistError() {
       emailContainer.error = "Email already exist."
    }

    // Listeners
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

    private fun datePickerConfirmBtnActionListener() {
        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            dateSelected =
                SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(selectedDate))
            birthDateEditText.setText(dateSelected)
            datePickerContainer.error = null
        }
    }

    private fun datePickerCancelBtnActionListener() {
        datePicker.addOnNegativeButtonClickListener {
            datePickerContainer.error = controller.validateDatePicker()
        }
    }

    private fun emailFocusListener() {
        emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                emailContainer.error = controller.validateEmail()
            }
        }
    }

    private fun passwordFocusListener() {
        passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                passwordContainer.error = controller.validatePassword()
                confirmPasswordContainer.error = controller.validateConfirmPassword()
            }
        }
    }

    private fun confirmPasswordFocusListener() {
        confirmPasswordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                confirmPasswordContainer.error = controller.validateConfirmPassword()
            }
        }
    }

}