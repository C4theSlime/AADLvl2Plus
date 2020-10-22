package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_match.view.*


class MatchAdapter(private val matches: List<Match>): RecyclerView.Adapter <MatchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind (match: Match){
            itemView.tvDate.text = match.matchDate.toString()
            matchResult(match)
            playerScore(match)
            computerScore(match)
        }
        private fun matchResult(matchObject: Match) {
            when (matchObject.matchResult) {
                0 -> itemView.tvResult.text = "Draw!"
                1 -> itemView.tvResult.text = "You win!"
                2 -> itemView.tvResult.text = "Computer wins!"
            }
        }

        private fun playerScore(matchObject: Match) {
            when (matchObject.playerMove) {
                0 -> itemView.playerResult.setImageResource(R.drawable.rock)
                1 -> itemView.playerResult.setImageResource(R.drawable.paper)
                2 -> itemView.playerResult.setImageResource(R.drawable.scissors)
            }
        }

        private fun computerScore(matchObject: Match) {
            when (matchObject.computerMove) {
                0 -> itemView.compResult.setImageResource(R.drawable.rock)
                1 -> itemView.compResult.setImageResource(R.drawable.paper)
                2 -> itemView.compResult.setImageResource(R.drawable.scissors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(matches[position])
    }


}