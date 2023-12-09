package com.example.cooksmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooksmart.view.profile.ProfileView

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileView: ProfileView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileView = ProfileView(this, null)
        setContentView(profileView.getRootView())
    }
}