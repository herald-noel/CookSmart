package com.example.cooksmart.model.profile

interface IProfileCallback {
    fun onProfileReceived(profile: ProfileModel)
    fun onProfileError(error: String)
}