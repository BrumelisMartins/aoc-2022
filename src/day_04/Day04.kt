package day_04

import readInput

fun main() {
    /**
     *
     */

    fun String.toSectionList(): List<Int> {
        val listOfSections = split('-').map { it.toInt() }
        return (listOfSections.first()..listOfSections.last()).toList()
    }

    fun String.toGroup(): Group {
        val groupStrings = split(',')
        return Group(groupStrings.first().toSectionList(), groupStrings.last().toSectionList())
    }

    fun List<String>.toListOfGroups(): List<Group> {
        return map {
            it.toGroup()
        }
    }

    fun doAllTasksOverlap(group: Group): Boolean {
        val intersectedList = group.firstAssignmentList.intersect(group.secondAssignmentList.toSet())
        val containsInFirstList = intersectedList.containsAll(group.firstAssignmentList)
        val containsInSecondList = intersectedList.containsAll(group.secondAssignmentList)
        return containsInFirstList || containsInSecondList
    }

    fun doesAnyTaskOverlap(group: Group): Boolean {
        return group.firstAssignmentList.intersect(group.secondAssignmentList.toSet()).isNotEmpty()
    }

    fun getSumOfOverlappingTasks(listOfGroups: List<Group>, areTasksOverlapping: (input: Group) -> Boolean): Int {
        return listOfGroups.sumOf { if (areTasksOverlapping(it)) 1.toInt() else 0 }
    }

    fun part1(input: List<String>): Int {
        return getSumOfOverlappingTasks(input.toListOfGroups(), ::doAllTasksOverlap)
    }

    fun part2(input: List<String>): Int {
        return getSumOfOverlappingTasks(input.toListOfGroups(), ::doesAnyTaskOverlap)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day_04/Day04_test")
    val input = readInput("day_04/Day04")

    println(part1(input))
    println(part2(input))
    println(part1(testInput))
}

data class Group(val firstAssignmentList: List<Int>, val secondAssignmentList: List<Int>)