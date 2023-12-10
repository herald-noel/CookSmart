package com.example.cooksmart.view.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.R
import com.example.cooksmart.adapter.DirectionAdapter
import com.example.cooksmart.api.model.instructions.InstructionsResponse
import com.example.cooksmart.controller.recipe.RecipeController
import com.example.cooksmart.data.Recipe
import com.example.cooksmart.model.base.Model
import com.example.cooksmart.model.recipe.RecipeModel
import com.example.cooksmart.view.base.CView
import com.example.recipeview.view.IRecipeView
import com.squareup.picasso.Picasso

class RecipeView(
    val context: Context, viewGroup: ViewGroup?,
    private var recipeId: Int,
    private var name: String,
    private var imgUrl: String,
    private var imgType: String
) : CView(), IRecipeView {
    override val view: View
    override val controller: RecipeController
    override val model: Model

    private val recipeIV: ImageView
    private val recipeName: TextView
    private val recyclerView: RecyclerView

    private val addFavoriteBtn: Button

    init {
        view = LayoutInflater.from(context).inflate(R.layout.activity_recipe, viewGroup)
        model = RecipeModel()
        controller = RecipeController(this)

        recipeIV = view.findViewById(R.id.recipeIV)
        recipeName = view.findViewById(R.id.recipeNTV)

        recyclerView = view.findViewById(R.id.recyclerV_Inst)
        recyclerView.layoutManager = LinearLayoutManager(context)

        addFavoriteBtn = view.findViewById(R.id.addFavoriteBtn)

        setImage()
        setName()
        initInstructions()

        favoriteBtnListener()
    }

    private fun setImage() {
        Picasso.get()
            .load(imgUrl)
            .into(recipeIV)
    }

    private fun setName() {
        recipeName.text = name
    }

    private fun initInstructions() {
        controller.managerGetRecipeDirection(recipeId)
    }

    override fun getRootView(): View {
        return view
    }

    fun setAdapter(response: InstructionsResponse) {
        recyclerView.adapter = DirectionAdapter(context, response[0].steps)
    }

    private fun favoriteBtnListener() {
        addFavoriteBtn.setOnClickListener {
            val recipe = Recipe(recipeId, name, imgUrl, imgType)
            controller.addRecipeFavoriteFirebase(recipe)
        }
    }

}
