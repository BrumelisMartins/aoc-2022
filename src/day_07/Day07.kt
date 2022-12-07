package day_07

import readInput
import java.util.ArrayDeque

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


    fun part2(): Int {
        val spaceRequiredForUpdate = 30000000
        val usedSpace = controller.hardDrive.size
        return controller.directorySizes.filter { (usedSpace - it) < (controller.totalSpace - spaceRequiredForUpdate) }
            .min()
    }

    println(part1())
    println(part2())

}