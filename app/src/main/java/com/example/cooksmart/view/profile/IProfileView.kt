package com.example.cooksmart.view.profile

import com.example.cooksmart.model.profile.ProfileModel
import com.example.cooksmart.view.base.IView

interface IProfileView: IView {
    fun updateProfile(profileModel: ProfileModel)
}