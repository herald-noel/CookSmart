package com.example.cooksmart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cooksmart.controller.home.HomeController
import com.example.cooksmart.view.home.HomeView
import java.io.IOException

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
        } else if (requestCode == HomeController.PICK_IMAGE_REQUEST) {
            val selectedImageUri = data?.data
            val bitmap: Bitmap? = selectedImageUri?.let { uriToBitmap(it) }
            bitmap?.let { homeView.detectImage(it) }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val resolver = contentResolver
            val inputStream = resolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
