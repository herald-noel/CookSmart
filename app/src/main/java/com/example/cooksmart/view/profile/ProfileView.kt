package com.example.cooksmart.view.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.cooksmart.R

import com.example.cooksmart.controller.profile.ProfileController
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.model.profile.ProfileModel
import com.example.cooksmart.view.base.CView
import com.squareup.picasso.Picasso

class ProfileView(private val context: Context, viewGroup: ViewGroup?) : CView(), IProfileView {

    override val view: View
    override var model: ProfileModel
    override val controller: ProfileController

    private var logout: Button
    private var favoriteBtn: ImageButton
    private var historyBtn: ImageButton
    private var backbutton: ImageButton

    private var emailTV: TextView
    private var bdTV: TextView

    private var favImageView1: ImageView
    private var favImageView2: ImageView
    private var favImageView3: ImageView

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

        favImageView1 = view.findViewById(R.id.favImageView1)
        favImageView2 = view.findViewById(R.id.favImageView2)
        favImageView3 = view.findViewById(R.id.favImageView3)

        controller.displayProfileDetails()

        backButtonListener()
        logoutButtonListener()
        favoritesButtonListener()
        historyButtonListener()
        initFavoriteRecipesThumbnail()
    }

    override fun getRootView(): View {
        return view
    }

    private fun logoutButtonListener() {
        logout.setOnClickListener {
            controller.logout(context)
        }
    }

    private fun favoritesButtonListener() {
        favoriteBtn.setOnClickListener {
            controller.redirectFavoriteRecipes(context)
        }
    }

    private fun historyButtonListener() {
        historyBtn.setOnClickListener {

        }
    }

    private fun backButtonListener() {
        backbutton.setOnClickListener {
            controller.redirectToHome(context)
        }
    }

    override fun updateProfile(profileModel: ProfileModel) {
        model = profileModel
        emailTV.text = model.email
        bdTV.text = model.birthdate
    }

    private fun initFavoriteRecipesThumbnail() {
        controller.showFavoriteRecipesThumbnail()
    }

    fun showFavoriteRecipes(favoriteRecipes: ArrayList<Recipe>) {
        val imgViews: ArrayList<ImageView> = arrayListOf(favImageView1, favImageView2, favImageView3)
        for ((index, recipe) in favoriteRecipes.withIndex()) {
            if (index > 2) {
                break
            }
            Picasso.get()
            .load(recipe.image)
            .into(imgViews[index])
        }
    }


}