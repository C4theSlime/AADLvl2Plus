package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_rps_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        getMatchesFromDatabase()

        initViews()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvHistory.adapter = matchAdapter
        rvHistory.setHasFixedSize(true)
        rvHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menu_history).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            mainScope.launch {
                deleteHistory()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteHistory() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchRepository.deleteAllMatches()
            }
            //getMatchListFromDatabase()
            getMatchesFromDatabase()
        }
    }

    private fun getMatchesFromDatabase() {
        mainScope.launch {
            val matches = withContext(Dispatchers.IO) {
                matchRepository.getAllMatches()
            }
            this@RPSHistoryFragment.matches.clear()
            this@RPSHistoryFragment.matches.addAll(matches)
            matchAdapter.notifyDataSetChanged()
        }

    }
}