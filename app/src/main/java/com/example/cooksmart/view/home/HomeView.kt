package com.example.cooksmart.view.home

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.viewpager2.widget.ViewPager2
import com.example.cooksmart.data.Ingredient
import com.example.cooksmart.R
import com.example.cooksmart.adapter.ViewPagerAdapter
import com.example.cooksmart.controller.home.HomeController
import com.example.cooksmart.fragments.IngredientFragment
import com.example.cooksmart.fragments.RecipeFragment
import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.home.HomeModel
import com.example.cooksmart.view.base.CView
import com.google.android.material.tabs.TabLayout
import org.tensorflow.lite.task.vision.detector.Detection
import java.util.ArrayList

class HomeView(private val context: Context, private val viewGroup: ViewGroup?) : CView(),
    View.OnClickListener {
    override val view: View = LayoutInflater.from(context).inflate(R.layout.activity_home, viewGroup)
    override val controller: HomeController
    override val model: Model

    private var ingredientSet: LinkedHashSet<Ingredient> = LinkedHashSet()
    private var ingredientList: ArrayList<Ingredient> = ArrayList()

    private var openGalleryBtn: Button
    private var addIngredientBtn: Button

    private var tabLayout: TabLayout

    private var viewPager2: ViewPager2

    private var tvPlaceholder: TextView

    private var captureImageFab: CardView

    private var inputImageView: ImageView
    private var imgSampleOne: ImageView
    private var imgSampleTwo: ImageView
    private var imgSampleThree: ImageView
    private var profileIcon: ImageView

    private var dialogView: View

    init {
        model = HomeModel()
        controller = HomeController(this)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager2 = view.findViewById(R.id.viewPager)
        captureImageFab = view.findViewById(R.id.captureImageFab)
        inputImageView = view.findViewById(R.id.imageView)
        imgSampleOne = view.findViewById(R.id.imgSampleOne)
        imgSampleTwo = view.findViewById(R.id.imgSampleTwo)
        imgSampleThree = view.findViewById(R.id.imgSampleThree)
        tvPlaceholder = view.findViewById(R.id.tvPlaceholder)
        openGalleryBtn = view.findViewById(R.id.openGalleryBtn)
        addIngredientBtn = view.findViewById(R.id.addIngredientBtn)
        profileIcon = view.findViewById(R.id.profile_icon)
        dialogView =
            LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_add_ingredient, viewGroup, false)

        captureImageFab.setOnClickListener(this)
        imgSampleOne.setOnClickListener(this)
        imgSampleTwo.setOnClickListener(this)
        imgSampleThree.setOnClickListener(this)
        openGalleryBtn.setOnClickListener(this)
        addIngredientBtn.setOnClickListener(this)
        profileIcon.setOnClickListener(this)
    }

    fun getIngredientSet(): LinkedHashSet<Ingredient> {
        return ingredientSet
    }

    fun getIngredientList(): ArrayList<Ingredient> {
        return ingredientList
    }

    fun getDialogView(): View {
        return dialogView
    }

    fun getContext(): Context {
        return context
    }

    fun getTvPlaceholder(): TextView {
        return tvPlaceholder
    }

    fun getInputImageView(): ImageView {
        return inputImageView
    }

    fun getCapturedImage(): Bitmap {
        return controller.getCapturedImage()
    }

    fun setControllerLifecycleScope(lifecycleScope: LifecycleCoroutineScope) {
        controller.setLifecycleScope(lifecycleScope)
    }

    fun setControllerActivity(activity: Activity) {
        controller.setActivity(activity)
    }

    fun setIngredientList(ingredientList: ArrayList<Ingredient>) {
        this.ingredientList = ingredientList
    }

    fun setIngredientSet(ingredientSet: LinkedHashSet<Ingredient>) {
        this.ingredientSet = ingredientSet
    }

    fun detectImage(bitmap: Bitmap) {
        controller.setViewAndDetect(bitmap)
    }

    override fun getRootView(): View {
        return view
    }

    fun setupViewPagerAndTabs(ingredientList: ArrayList<Ingredient>) {
        // Pass the ingredientList to the ViewPagerAdapter
        val ingredientFragment = IngredientFragment(ingredientList, controller)
        val recipeFragment = RecipeFragment.newInstance(ingredientList)
        ingredientFragment.setIngredientFragmentListener(recipeFragment)
        viewPager2.adapter =
            ViewPagerAdapter(context as FragmentActivity, ingredientFragment, recipeFragment)

        // Set up the TabLayout and ViewPager2 interaction
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    fun setControllerViewAndDetect(capturedImage: Bitmap) {
        controller.setViewAndDetect(capturedImage)
    }

    /**
     * onClick(v: View?)
     *      Detect touches on the UI components
     */
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.captureImageFab -> {
                try {
                    controller.dispatchTakePictureIntent()
                } catch (e: ActivityNotFoundException) {
                    Log.e(HomeController.TAG, e.message.toString())
                }
            }

            R.id.imgSampleOne -> {
                controller.setViewAndDetect(controller.getSampleImage(R.drawable.img_meal_one))
            }

            R.id.imgSampleTwo -> {
                controller.setViewAndDetect(controller.getSampleImage(R.drawable.img_meal_two))
            }

            R.id.imgSampleThree -> {
                controller.setViewAndDetect(controller.getSampleImage(R.drawable.img_meal_three))
            }

            R.id.openGalleryBtn -> {
                controller.openGallery()
            }

            R.id.addIngredientBtn -> {
                controller.showIngredientDialog()
            }
            R.id.profile_icon -> {
                controller.openProfile()
            }
        }
    }

}