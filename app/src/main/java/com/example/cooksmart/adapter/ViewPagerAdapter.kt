package com.example.cooksmart.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cooksmart.fragments.IngredientFragment
import com.example.cooksmart.fragments.RecipeFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val ingredientFragment: IngredientFragment,
    private val recipeFragment: RecipeFragment
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ingredientFragment
            1 -> recipeFragment
            else -> ingredientFragment
        }
    }


}

