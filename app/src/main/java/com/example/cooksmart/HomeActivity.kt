package com.example.cooksmart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.lifecycleScope
import com.example.cooksmart.controller.home.HomeController
import com.example.cooksmart.view.home.HomeView
import kotlin.math.max
import kotlin.math.min

class HomeActivity : AppCompatActivity() {
    private lateinit var homeView: HomeView

    override fun onCreate(savedInstanceState: Bundle?) {
        homeView = HomeView(this, null)
        homeView.setControllerActivity(this)
        homeView.setControllerLifecycleScope(lifecycleScope)
        super.onCreate(savedInstanceState)
        setContentView(homeView.getRootView())
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == HomeController.REQUEST_IMAGE_CAPTURE &&
            resultCode == Activity.RESULT_OK
        ) {
            homeView.setControllerViewAndDetect(homeView.getCapturedImage())
        }
    }
}
