package com.example.cooksmart.view.base

import android.view.View
import com.example.cooksmart.controller.base.Controller
import com.example.cooksmart.model.base.Model

abstract class CView: IView {
    abstract val view: View
    abstract val controller: Controller
    abstract val model: Model
}