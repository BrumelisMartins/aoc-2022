package day_10

import readInput

fun main() {

    val input = readInput("day_10/Day10")
    val testInput = readInput("day_10/Day10_test")

    val cpu = CPU(input.map { CPUOutput.fromString(it) })

    fun part1(): Int {
        return cpu.sumOfSignalStrengths
    }

    println(part1())

}