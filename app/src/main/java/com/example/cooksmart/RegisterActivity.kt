package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cooksmart.view.register.RegisterView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerView: RegisterView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerView = RegisterView(this, null)
        setContentView(registerView.getRootView())
    }

    // TODO refactor function to MVC
    fun showDatePickerDialog(view: View) {
        // Create a MaterialDatePicker
        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        // Set a listener to handle the selected date
        datePicker.addOnPositiveButtonClickListener { selection ->
            // Handle the selected date
            // Update the TextInputEditText with the selected date
            //val editText = findViewById<TextInputEditText>(R.id.birthdateRegisterEditText)
            //editText.setText(datePicker.headerText)
        }
        // Show the date picker
        datePicker.show(supportFragmentManager, datePicker.toString())
    }
}
