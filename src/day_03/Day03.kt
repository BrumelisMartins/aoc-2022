package day_03

import readInput

fun main() {
    /**
     *
     */

    val alphabet = (('a'..'z').toList() + ('A'..'Z').toList()).map { it.toString() }

    fun String.toPriority(): Int {
        return alphabet.indexOf(this) + 1
    }

    fun String.toBag(): Bag {
        val compartmentList = chunked(length / 2) { bunchOfItems: CharSequence -> bunchOfItems.chunked(1) }
        return Bag(compartmentList.first(), compartmentList.last())
    }

    fun String.toMessyBag(): MessyBag {
        return MessyBag(chunked(1))
    }

    fun findPriorityItem(bag: Bag): String {
        val listOfDuplicates = bag.firstCompartment.intersect(bag.secondCompartment.toSet())
        return listOfDuplicates.first()
    }

    fun findPriorityItemFromMultipleBags(listOfBags: List<MessyBag>): String{
        var setOfBags = listOfBags.first().bagContent.toSet()
        listOfBags.forEachIndexed { index, _ ->
            if (index >= listOfBags.size - 1) return@forEachIndexed
            val secondBag = listOfBags[index + 1]
            setOfBags = setOfBags.intersect(secondBag.bagContent.toSet())
        }
        return setOfBags.first()
    }

    fun getPrioritySum(listOfBags: List<Bag>): Int {
        return listOfBags.sumOf { findPriorityItem(it).toPriority() }
    }

    fun getPrioritySumFromMultipleGroups(listOfBags: List<List<MessyBag>>): Int {
        return listOfBags.sumOf { findPriorityItemFromMultipleBags(it).toPriority() }
    }

    fun part1(input: List<String>): Int {
        val listOfBags = input.map { it.toBag() }
        return getPrioritySum(listOfBags)
    }

    fun part2(input: List<String>): Int {
        val listOfGroupBags = input.map { it.toMessyBag() }.windowed(3, 3)
        return getPrioritySumFromMultipleGroups(listOfGroupBags)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_03/Day03_test")

    check(part1(testInput) == 157)

    val input = readInput("day_03/Day03")
    println(part1(input))
    println(part2(input))
    println(part2(testInput))
}

data class Bag(val firstCompartment: List<String>, val secondCompartment: List<String>)
data class MessyBag(val bagContent: List<String>)
