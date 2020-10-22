package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_rps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RPSFragment : Fragment() {
    private lateinit var matchRepository: MatchRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val matches = arrayListOf<Match>()

    private var playerDecision = 0
    private var compDecision = 0
    private var matchEnd = 0 //0 is a tie, 1 is player win, 2 is comp win

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        matchRepository = MatchRepository(requireContext())
        mainScope.launch { initViews() }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menu_back).isVisible = false
        menu.findItem(R.id.menu_delete).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun initViews() {
        initMatch()

        btn_rock.setOnClickListener {
            playerChoice.setImageResource(R.drawable.rock)
            compChoice.setImageResource(randomChoice())
            playerDecision = 0

            mainScope.launch {
                loadMatch()
            }
        }
        btn_paper.setOnClickListener {
            playerChoice.setImageResource(R.drawable.paper)
            compChoice.setImageResource(randomChoice())
            playerDecision = 1

            mainScope.launch {
                loadMatch()
            }
        }
        btn_scissors.setOnClickListener {
            playerChoice.setImageResource(R.drawable.scissors)
            compChoice.setImageResource(randomChoice())
            playerDecision = 2

            mainScope.launch {
                loadMatch()
            }
        }
    }

    private fun loadMatch() {
        tvResultMatch.isVisible = true
        versusText.isVisible = true
        computerText.isVisible = true
        playerText.isVisible = true
        playerChoice.isVisible = true
        compChoice.isVisible = true

        when (playerDecision) {
            0 -> {
                if (compDecision == 0) matchEnd = 0
                else if (compDecision == 1) matchEnd = 2
                else matchEnd = 1
            }
            1 -> {
                if (compDecision == 0) matchEnd = 1
                else if (compDecision == 1) matchEnd = 0
                else matchEnd = 2
            }
            2 -> {
                if (compDecision == 0) matchEnd = 2
                else if (compDecision == 1) matchEnd = 1
                else matchEnd = 0
            }
        }

        when (matchEnd) {
            0 -> tvResultMatch.text = "Draw!"
            1 -> tvResultMatch.text = "You win!"
            2 -> tvResultMatch.text = "Computer wins!"
        }
        mainScope.launch { addMatch() }

    }

    private suspend fun addMatch() {
        val match = Match (
            matchDate = Date(System.currentTimeMillis()),
            matchResult = matchEnd,
            computerMove = compDecision,
            playerMove = playerDecision
        )

        withContext(Dispatchers.IO){
            matchRepository.insertMatch(match)
        }

        getMatchListFromDatabase()
    }

    private suspend fun getMatchListFromDatabase() {
        mainScope.launch {
            withContext(Dispatchers.IO){
                val matchList = withContext(Dispatchers.IO) {
                    matchRepository.getAllMatches()
                }

                this@RPSFragment.matches.clear()
                this@RPSFragment.matches.addAll(matchList)
            }
        }
    }

    private fun initMatch() {
        tvResultMatch.isVisible = false
        versusText.isVisible = false
        computerText.isVisible = false
        playerText.isVisible = false
        playerChoice.isVisible = false
        compChoice.isVisible = false
    }

    private fun randomChoice(): Int {
        val randomThrow = listOf(0, 1, 2).random()
        when (randomThrow) {
            0 -> compDecision = 0
            1 -> compDecision = 1
            2 -> compDecision = 2
        }
        return when (randomThrow) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            else -> R.drawable.rock
        }

    }
}