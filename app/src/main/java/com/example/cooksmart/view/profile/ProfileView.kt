package com.example.cooksmart.view.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.cooksmart.R

import com.example.cooksmart.controller.profile.ProfileController
import com.example.cooksmart.model.profile.ProfileModel
import com.example.cooksmart.view.base.CView

class ProfileView (private val context: Context,viewGroup: ViewGroup?) : CView(), IProfileView {

    override val view: View
    override var model: ProfileModel
    override val controller: ProfileController

    private var logout: Button
    private var favoriteBtn: ImageButton
    private var historyBtn: ImageButton
    private var backbutton: ImageButton

    private var emailTV: TextView
    private var bdTV: TextView
    init {
        model = ProfileModel()
        controller = ProfileController(model, this)
        view = LayoutInflater.from(context).inflate(R.layout.activity_profile, viewGroup)

        logout = view.findViewById(R.id.logoutBtn)
        favoriteBtn = view.findViewById(R.id.favoriteBtn)
        historyBtn = view.findViewById(R.id.historyBtn)
        backbutton = view.findViewById(R.id.backButton)

        emailTV = view.findViewById(R.id.profileName2)
        bdTV = view.findViewById(R.id.profileName3)

        controller.displayProfileDetails()

        backButtonListener()
        logoutButtonListener()
        favoritesButtonListener()
        historyButtonListener()
    }

    override fun getRootView(): View {
        return view
    }

    private fun logoutButtonListener(){
        logout.setOnClickListener(){
            controller.logout(context)
        }
    }
    private fun favoritesButtonListener(){
        favoriteBtn.setOnClickListener {

        }
    }
    private fun historyButtonListener(){
        historyBtn.setOnClickListener {

        }
    }

    private fun backButtonListener(){
        backbutton.setOnClickListener(){
            controller.redirectToHome(context)
        }
    }

    override fun updateProfile(profileModel: ProfileModel) {
        model = profileModel
        emailTV.text = model.email
        bdTV.text = model.birthdate
    }
}