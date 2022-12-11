package day_04

import readInput

fun main() {
    fun doAllTasksOverlap(group: Group): Boolean {
        val intersectedList = group.firstAssignmentList.intersect(group.secondAssignmentList.toSet())
        val containsInFirstList = intersectedList.containsAll(group.firstAssignmentList)
        val containsInSecondList = intersectedList.containsAll(group.secondAssignmentList)
        return containsInFirstList || containsInSecondList
    }

    fun doesAnyTaskOverlap(group: Group) =
        group.firstAssignmentList.intersect(group.secondAssignmentList.toSet())
            .isNotEmpty()


    fun getSumOfOverlappingTasks(listOfGroups: List<Group>, areTasksOverlapping: (input: Group) -> Boolean) =
        listOfGroups.sumOf { if (areTasksOverlapping(it)) 1.toInt() else 0 }

    fun part1(input: List<String>) =
        getSumOfOverlappingTasks(input.toListOfGroups(), ::doAllTasksOverlap)

    fun part2(input: List<String>) =
        getSumOfOverlappingTasks(input.toListOfGroups(), ::doesAnyTaskOverlap)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_04/Day04_test")
    val input = readInput("day_04/Day04")

    println(part1(input))
    println(part2(input))
    println(part1(testInput))
}