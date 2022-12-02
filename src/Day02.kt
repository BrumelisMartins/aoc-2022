fun main() {
    /**
     * Win = 6
     * Draw = 3
     * Loss = 0
     *
     * Rules
     * ELF
     * A = Rock
     * B = Paper
     * C = Scissors
     * ME
     * X = Rock
     * Y = Paper
     * Z = Scissors
     *
     *
     */

    fun getMatchScore(elfGesture: Gesture, myGesture: Gesture): Int {
        return if (myGesture.betterThan == elfGesture.id) {
            6
        } else if (myGesture == elfGesture){
            3
        } else {
            0
        }
    }
    val gestureList = Gesture.values()

    fun toGesture(string: String): Gesture? {
        return when (string) {
            "A" -> Gesture.ROCK
            "B" -> Gesture.PAPER
            "C" -> Gesture.SCISSORS
            "X" -> Gesture.ROCK
            "Y" -> Gesture.PAPER
            "Z" -> Gesture.SCISSORS
            else -> null

        }
    }

    fun getTotalScore(input: List<String>): Int {
        val listOfGestures = input.map{ raw -> raw.split(" ").map { toGesture(it) }  }
        var sum = 0
        listOfGestures.forEach {
            val elfGesture = it.first()
            val myGesture = it.last()
            if (elfGesture == null || myGesture == null) return@forEach
            sum += getMatchScore(elfGesture, myGesture) + myGesture.score
        }
        return sum
    }

    fun getFixedMatchScore(elfGesture: Gesture, myGesture: String): Int {
        return when (myGesture) {
            "X" -> {
                //lose
                val losingGesture = gestureList.find { it.worseThan == elfGesture.id }
                losingGesture!!.score
            }
            "Y" -> {
                //draw
                3 + elfGesture.score
            }
            else -> {
                //win
                val winingGesture = gestureList.find { it.betterThan == elfGesture.id }
                winingGesture!!.score + 6
            }
        }
    }

    fun getFixedTotalResults(input: List<String>): Int {
        val listOfGestures = input.map{ raw -> raw.split(" ") }
        var sum = 0
        listOfGestures.forEach {
            val elfGesture = toGesture(it.first())
            val myGesture = it.last()
            if (elfGesture == null) return@forEach
            sum += getFixedMatchScore(elfGesture, myGesture)
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        return getTotalScore(input)
    }

    fun part2(input: List<String>): Int {
        return getFixedTotalResults(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Gesture(val score: Int,val  id: String,val  betterThan: String,val  worseThan: String) {
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
