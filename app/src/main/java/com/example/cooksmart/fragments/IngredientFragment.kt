package com.example.cooksmart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cooksmart.data.Ingredient
import com.example.cooksmart.IngredientAdapter
import com.example.cooksmart.IngredientFragmentListener
import com.example.cooksmart.R
import com.example.cooksmart.listener.OnIngredientChangedListener

class IngredientFragment : Fragment(), IngredientAdapter.OnRemoveClickListener {

    private lateinit var ingredientList: MutableList<Ingredient>
    private lateinit var adapter: IngredientAdapter
    private var listener: IngredientFragmentListener? = null
    private var controllerListener: OnIngredientChangedListener? = null

    fun setIngredientFragmentListener(listener: IngredientFragmentListener) {
        this.listener = listener
    }

    fun setControllerListener(listener: OnIngredientChangedListener) {
        controllerListener = listener
    }

    private fun notifyRecipe(updateIngredient: MutableList<Ingredient>) {
        listener?.onIngredientChange(updateIngredient)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.getParcelableArrayList<Ingredient>(ARG_INGREDIENTS_LIST)?.let {
            // Initialize ingredientList with the retrieved ArrayList
            ingredientList = it.toMutableList()
        } ?: run {
            // Handle case where ingredients_key is not present in arguments
            ingredientList = mutableListOf()
        }
        return inflater.inflate(R.layout.fragment_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        // Initialize the adapter with the ingredientList and this fragment as the listener
        adapter = IngredientAdapter(ingredientList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    // Implementation of the remove click listener
    override fun onRemoveClick(position: Int) {
        // Remove the item from the list
        ingredientList.removeAt(position)

        notifyRecipe(ingredientList)
        controllerListener!!.onRemoveIngredient(ArrayList(ingredientList))

        // Notify the adapter about the removal
        adapter.notifyItemRemoved(position)
    }

    fun setIngredientList(ingredientList: ArrayList<Ingredient>) {
        this.ingredientList = ingredientList
    }

    companion object {
        private const val ARG_INGREDIENTS_LIST = "ingredients_key"
        fun newInstance(ingredientList: ArrayList<Ingredient>): IngredientFragment {
            val fragment = IngredientFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(ARG_INGREDIENTS_LIST, ingredientList)
            fragment.arguments = bundle
            return fragment
        }
    }
}


