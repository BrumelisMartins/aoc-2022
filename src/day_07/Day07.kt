package day_07

import readInput

fun main() {
    val mapper = SystemMapper()

    val input = mapper.getData(readInput("day_07/Day07"))
    val testInput = mapper.getData(readInput("day_07/Day07_test"))
    val controller = DirectoryController()

    input.forEach {
        controller.handleSystemOutput(it)
    }

    fun part1(): Int {
        return controller.directorySizes
            .sumOf { if (it < 100000) it else 0 }
    }

    fun isLargeEnough(directorySize: Int): Boolean {
        val spaceRequiredForUpdate = 30000000
        return controller.freeSpace + directorySize > spaceRequiredForUpdate
    }

    fun part2(): Int {
        return controller.directorySizes.filter { isLargeEnough(it) }
            .min()
    }

    println(part1())
    println(part2())

}