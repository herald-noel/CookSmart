package com.example.cooksmart.view.login

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cooksmart.R
import com.example.cooksmart.controller.login.LoginController
import com.example.cooksmart.model.login.LoginModel
import com.example.cooksmart.view.base.CView
import com.google.android.material.textfield.TextInputLayout

class LoginView(private val context: Context, viewGroup: ViewGroup?) : CView(), ILoginView {
    // MVC Variables
    override val model: LoginModel
    override val controller: LoginController
    override val view: View

    // EditText
    private var emailEditText: EditText
    private var passwordEditText: EditText

    // Button
    private var loginBtn: Button
    private var registerBtn: Button

    // TextInputLayout
    private var emailContainer: TextInputLayout
    private var passwordContainer: TextInputLayout

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_login, viewGroup)
        model = LoginModel()
        controller = LoginController(model, this)

        // Initialize EditText
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)

        // Initialize Button
        loginBtn = view.findViewById(R.id.loginBtn)
        registerBtn = view.findViewById(R.id.registerBtn)

        // Initialize Container
        emailContainer = view.findViewById(R.id.emailLoginContainer)
        passwordContainer = view.findViewById(R.id.passwordLoginContainer)

        // Listeners
        loginBtnActionListener()
        registerBtnActionListener()
        emailFocusListener()
        passwordFocusListener()
    }


    override fun getEmailText(): String {
        return emailEditText.text.toString()
    }

    override fun getPasswordText(): String {
        return passwordEditText.text.toString()
    }

    override fun getRootView(): View {
        return view
    }

    fun getEmailContainer(): TextInputLayout {
        return emailContainer
    }

    fun getPasswordContainer(): TextInputLayout {
        return passwordContainer
    }

    fun showSuccessToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showErrorToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Listeners
    private fun loginBtnActionListener() {
        loginBtn.setOnClickListener {
            controller.getLoginStatus(getEmailText(), getPasswordText())
        }
    }

    private fun registerBtnActionListener() {
        registerBtn.setOnClickListener {
            controller.redirectRegister(context)
        }
    }


    private fun emailFocusListener() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }

            override fun afterTextChanged(p0: Editable?) {
                emailContainer.error = controller.validateEmail()
            }
        })
    }

    private fun passwordFocusListener() {
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
            }

            override fun afterTextChanged(p0: Editable?) {
                passwordContainer.error = controller.validatePassword()
            }
        })
    }
}