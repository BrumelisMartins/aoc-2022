package day_08

import readInput

fun main() {

    val input = readInput("day_08/Day08")
    val testInput = readInput("day_08/Day08_test")

    fun part1(): Int {
        return Forrest.fromRawData(input).numberOfVisibleTrees
    }

    fun part2(): Int {
        return Forrest.fromRawData(input).trees.maxOf { it.totalScore }
    }

    println(part1())
    println(part2())

}