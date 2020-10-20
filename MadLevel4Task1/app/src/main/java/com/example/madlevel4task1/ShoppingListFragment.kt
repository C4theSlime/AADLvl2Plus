package com.example.madlevel4task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_shopping_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingListFragment : Fragment() {
    private val products = arrayListOf<Product>()
    private val shoppingAdapter = ShoppingAdapter(products)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        rvShopping.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvShopping.adapter = shoppingAdapter
        rvShopping.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    }

}