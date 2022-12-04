package day_02

import readInput

fun main() {
    fun getMatchScore(elfGesture: Gesture, myGesture: Gesture): Int {
        return if (myGesture.betterThan == elfGesture.id) {
            6
        } else if (myGesture == elfGesture) {
            3
        } else {
            0
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

    fun part1(input: List<String>): Int {
        val convertedList = convertStringListToGesturePairs(input, false)
        return getTotalScore(convertedList)
    }

    fun part2(input: List<String>): Int {
        val convertedList = convertStringListToGesturePairs(input, true)
        return getTotalScore(convertedList)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_02/Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("day_02/Day02")
    println(part1(input))
    println(part2(input))
}
