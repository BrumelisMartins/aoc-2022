package day_02

enum class Gesture(val score: Int, val id: String, val betterThan: String, val worseThan: String) {
    ROCK(
        score = 1,
        id = "ROCK",
        betterThan = "SCISSORS",
        worseThan = "PAPER"
    ),
    PAPER(
        score = 2,
        id = "PAPER",
        betterThan = "ROCK",
        worseThan = "SCISSORS"
    ),
    SCISSORS(
        score = 3,
        id = "SCISSORS",
        betterThan = "PAPER",
        worseThan = "ROCK"
    )
}