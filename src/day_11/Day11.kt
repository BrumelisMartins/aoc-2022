package day_11

import day_04.Group
import readInput

fun main() {

    val testMonkey0 = Monkey(startingItems = ArrayDeque(listOf(79, 98)), operation = { return@Monkey it * 19 }, divisor = 23, test = { return@Monkey if (it % 23 == 0L) 2 else 3 })
    val testMonkey1 = Monkey(startingItems = ArrayDeque(listOf(54, 65, 75, 74)), operation = { return@Monkey it + 6 }, divisor = 19, test = { return@Monkey if (it % 19 == 0L) 2 else 0 })
    val testMonkey2 = Monkey(startingItems = ArrayDeque(listOf(79, 60, 97)), operation = { return@Monkey it * it }, divisor = 13, test = { return@Monkey if (it % 13 == 0L) 1 else 3 })
    val testMonkey3 = Monkey(startingItems = ArrayDeque(listOf(74)), operation = { return@Monkey it + 3 }, divisor = 17, test = { return@Monkey if (it % 17 == 0L) 0 else 1 })

    val monkey0 = Monkey(startingItems = ArrayDeque(listOf(76, 88, 96, 97, 58, 61, 67)), operation = { return@Monkey it * 19 }, divisor = 3, test = { return@Monkey if (it % 3 == 0L) 2 else 3 })
    val monkey1 = Monkey(startingItems = ArrayDeque(listOf(93, 71, 79, 83, 69, 70, 94, 98)), operation = { return@Monkey it + 8 }, divisor = 11, test = { return@Monkey if (it % 11 == 0L) 5 else 6 })
    val monkey2 = Monkey(startingItems = ArrayDeque(listOf(50, 74, 67, 92, 61, 76)), operation = { return@Monkey it * 13 }, divisor = 19, test = { return@Monkey if (it % 19 == 0L) 3 else 1 })
    val monkey3 = Monkey(startingItems = ArrayDeque(listOf(76, 92)), operation = { return@Monkey it + 6 }, divisor = 5, test = { return@Monkey if (it % 5 == 0L) 1 else 6 })
    val monkey4 = Monkey(startingItems = ArrayDeque(listOf(74, 94, 55, 87, 62)), operation = { return@Monkey it + 5 }, divisor = 2, test = { return@Monkey if (it % 2 == 0L) 2 else 0 })
    val monkey5 = Monkey(startingItems = ArrayDeque(listOf(59, 62, 53, 62)), operation = { return@Monkey it * it }, divisor = 7, test = { return@Monkey if (it % 7 == 0L) 4 else 7 })
    val monkey6 = Monkey(startingItems = ArrayDeque(listOf(62)), operation = { return@Monkey it + 2 }, divisor = 17, test = { return@Monkey if (it % 17 == 0L) 5 else 7 })
    val monkey7 = Monkey(startingItems = ArrayDeque(listOf(85, 54, 53)), operation = { return@Monkey it + 3 }, divisor = 13, test = { return@Monkey if (it % 13 == 0L) 4 else 0 })

    val listOfMonkeys = listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)
    val testList = listOf(testMonkey0, testMonkey1, testMonkey2, testMonkey3)

    val lcm = listOfMonkeys.map { it.divisor }
        .reduce { acc, bigInteger -> acc * bigInteger }

    fun shuffleItems(divideBy: Int) {
        listOfMonkeys.forEach { monkey ->
            monkey.startingItems.forEach { item ->
                val newWorryLevel = monkey.operation(item) / divideBy
                val relief = newWorryLevel % lcm
                listOfMonkeys[monkey.test(newWorryLevel)].startingItems.addLast(relief)
                monkey.inspectionCount++
            }
            monkey.startingItems.clear()
        }
    }

    fun getMonkeyBusiness(): Long {
        val mostActiveMonkeys = listOfMonkeys.sortedByDescending { it.inspectionCount }
            .take(2)
        return mostActiveMonkeys.first().inspectionCount * mostActiveMonkeys.last().inspectionCount
    }

    fun part1(): Long {
        repeat(20) {
            shuffleItems(3)
        }
        return getMonkeyBusiness()
    }

    fun part2(): Long {
        repeat(10000) {
            shuffleItems(1)
        }
        return getMonkeyBusiness()
    }

    println(part2())


}

class Monkey(val startingItems: ArrayDeque<Long>, val divisor: Int, val operation: (input: Long) -> Long, val test: (input: Long) -> Int, var inspectionCount: Long = 0)