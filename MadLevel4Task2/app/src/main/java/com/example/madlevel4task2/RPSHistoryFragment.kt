package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RPSHistoryFragment : Fragment() {
    private lateinit var matchRepository: MatchRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val matches = arrayListOf<Match>()
    private val matchAdapter = MatchAdapter(matches)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rps_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        matchRepository = MatchRepository(requireActivity().applicationContext)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menu_history).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }
}