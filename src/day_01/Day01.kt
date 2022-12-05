package day_01

import readInput

fun main() {
    fun getListOfCalories(input: List<String>): List<Int> {
        val listOfSums = arrayListOf<Int>()
        var sum = 0
        input.forEach {
            if (it.isBlank()) {
                listOfSums.add(sum)
                sum = 0
                return@forEach
            }
            sum += it.toInt()
        }
        //in case last element is not an empty line and was not added
        if (sum != 0) {
            listOfSums.add(sum)
        }
        return listOfSums
    }


    fun part1(input: List<String>): Int {
        val listOfSums = getListOfCalories(input)
        return listOfSums.max()
    }

    fun part2(input: List<String>): Int {
        val listOfSums = getListOfCalories(input).sortedByDescending { it }
        return listOfSums.take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_01/Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("day_01/Day01")
    println(part1(input))
    println(part2(input))
}
