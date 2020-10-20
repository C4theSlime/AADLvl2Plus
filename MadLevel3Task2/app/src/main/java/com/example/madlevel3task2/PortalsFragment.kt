package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter

//        for (i in Portal.PORTAL_TITLES.indices) {
//            portals.add(Portal(Portal.PORTAL_TITLES[i], Portal.PORTAL_URL[i]))
//        }
//        portalAdapter.notifyDataSetChanged()
    }
}