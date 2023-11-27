package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cooksmart.view.register.RegisterView

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerView: RegisterView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerView = RegisterView(this, null)
        setContentView(registerView.getRootView())
    }

    fun showDatePickerDialog(view: View) {
        registerView.showDatePickerDialog()
    }
}
