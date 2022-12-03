package day_03

import day_02.convertStringListToGesturePairs
import day_02.getTotalScore
import readInput

fun main() {
    /**
     *
     */

    fun String.toPriority(): Int {
        return 0
    }

    fun String.toBag(): Bag {
        val compartmentList = chunked(length) { bunchOfItems: CharSequence -> bunchOfItems.chunked(1) }
        return Bag(compartmentList.first(), compartmentList.last())
    }

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_03/Day03_test")
    val listOfBags = testInput.map { it.toBag() }
    listOfBags.forEach {
        println(it.toString())
    }
//    check(part1(testInput) == 15)
//
//    val input = readInput("Day03")
//    println(part1(input))
//    println(part2(input))
}

data class Bag(val firstCompartment: List<String>, val secondCompartment: List<String>)
