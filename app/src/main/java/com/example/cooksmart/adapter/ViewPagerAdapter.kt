package com.example.cooksmart

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cooksmart.fragments.IngredientFragment
import com.example.cooksmart.fragments.RecipeFragment
class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val ingredientList: ArrayList<Ingredient>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IngredientFragment.newInstance(ingredientList)
            1 -> RecipeFragment.newInstance(ingredientList)
            else -> IngredientFragment.newInstance(ingredientList)
        }
    }
}

