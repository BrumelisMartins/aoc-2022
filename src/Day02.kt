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

    fun part1(input: List<String>): Int {
        val convertedList = convertStringListToGesturePairs(input, false)
        return getTotalScore(convertedList)
    }

    fun part2(input: List<String>): Int {
        val convertedList = convertStringListToGesturePairs(input, true)
        return getTotalScore(convertedList)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
val gestureList = Gesture.values()

fun getMatchScore(elfGesture: Gesture, myGesture: Gesture): Int {
    return if (myGesture.betterThan == elfGesture.id) {
        6
    } else if (myGesture == elfGesture) {
        3
    } else {
        0
    }
}

fun String.toGestureFromCode(elfGesture: Gesture?): Gesture? {
    return when (this) {
        "X" -> {
            //lose
            gestureList.find { it.worseThan == elfGesture?.id }
        }
        "Z" -> {
            //win
            gestureList.find { it.betterThan == elfGesture?.id }
        }
        else -> {
            //draw
            elfGesture
        }
    }
}

fun String.toGesture(): Gesture? {
    return when (this) {
        "A" -> Gesture.ROCK
        "B" -> Gesture.PAPER
        "C" -> Gesture.SCISSORS
        "X" -> Gesture.ROCK
        "Y" -> Gesture.PAPER
        "Z" -> Gesture.SCISSORS
        else -> null
    }
}

fun getTotalScore(listOfGestures: List<Pair<Gesture?, Gesture?>>): Int {
    var sum = 0
    listOfGestures.forEach {
        val elfGesture = it.first
        val myGesture = it.second
        if (elfGesture == null || myGesture == null) return@forEach
        sum += getMatchScore(elfGesture, myGesture) + myGesture.score
    }
    return sum
}

fun convertStringListToGesturePairs(
    rawList: List<String>,
    useSecretElfCode: Boolean
): List<Pair<Gesture?, Gesture?>> {
    val listOfGestures = rawList.map { raw ->
        val newList = raw.split(" ")
        val elfGesture = newList.first().toGesture()
        val myGesture = if (useSecretElfCode) {
            newList.last().toGestureFromCode(elfGesture)
        } else {
            newList.last().toGesture()
        }
        Pair(elfGesture, myGesture)
    }
    return listOfGestures
}

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
