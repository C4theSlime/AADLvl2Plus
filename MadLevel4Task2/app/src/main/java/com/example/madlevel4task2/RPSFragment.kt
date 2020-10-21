package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rps.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RPSFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menu_back).isVisible = false
        menu.findItem(R.id.menu_delete).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun initViews() {
        initMatch()
    }

    private fun initMatch() {
        tvResultMatch.isVisible = false
        versusText.isVisible = false
        computerText.isVisible = false
        playerText.isVisible = false

        playerChoice.isVisible = false
        compChoice.isVisible = false
    }


}