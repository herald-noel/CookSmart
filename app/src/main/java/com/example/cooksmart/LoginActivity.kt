package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.login.LoginView

class LoginActivity : AppCompatActivity() {
    private lateinit var loginView: LoginView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginView = LoginView(this, null)
        setContentView(loginView.getRootView())
    }
}