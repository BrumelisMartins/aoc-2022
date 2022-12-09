package day_09

import readInput

fun main() {

    val input = readInput("day_09/Day09")
    val bridge = Bridge.fromRawData(input).apply { runMovementSequence() }

    fun part1(): Int {
        return bridge.tailStepCount
    }

    fun part2(): Int {
        return bridge.ropeStepCount
    }

    println(part1())
    println(part2())

}

