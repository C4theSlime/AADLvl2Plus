package com.example.madlevel2task2

data class Question (
    var questionText: String
) {
    companion object {
        val QUESTIONS = arrayOf(
            "Using an AND gate with the inputs T and F returns True.",
            "Val in Kotlin acts like a 'final' variable in other languages",
            "1 + 1 = 3"
        )
    }
}