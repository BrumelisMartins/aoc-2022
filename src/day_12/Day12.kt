package day_12

import day_03.alphabet
import readInput
import java.util.*
import kotlin.collections.ArrayList


fun main() {

    val input = readInput("day_12/Day12")
    val testInput = fromRawData(readInput("day_12/Day12_test"))


    fun part1(): Int {
        val finder = PathFinder(fromRawData(input))
        finder.run()
        val shortest = finder.findShortestPath()


        val newlist = finder.sourceDataGrid.map { if (shortest.contains(it)) it.value else "." }
            .chunked(input.first().length)
        newlist.forEach {
            it.forEach { string ->
                print(string)
            }
            println()
        }
        println(shortest.size)
        return 0
    }

    println(part1())

}

fun fromRawData(rawData: List<String>): List<Node> {
    val data = rawData.map { it.toList() }
        .mapIndexed { row, list ->
            list.mapIndexed { index, s ->
                val indexSize = if (row == 0) 0 else list.size * row
                Node(index + indexSize, s.toString())
            }
        }

    val listOfNodes = mutableListOf<Node>()
    (data.indices).flatMap { row ->
        (data[row].indices).map { column ->
            val current = data[row][column]

            current.addAdjacentNode(data.getOrNull(row - 1)
                ?.getOrNull(column))
            current.addAdjacentNode(data.getOrNull(row + 1)
                ?.getOrNull(column))
            current.addAdjacentNode(data.getOrNull(row)
                ?.getOrNull(column - 1))
            current.addAdjacentNode(data.getOrNull(row)
                ?.getOrNull(column + 1))
            listOfNodes.add(current)
        }
    }
    return listOfNodes
}


fun isAdjacent(current: Node, childNode: Node?): Boolean {
    if (childNode == null) return false
    val height = alphabet.indexOf(current.value.mapToElevation())
    val childHeight = alphabet.indexOf(childNode.value.mapToElevation())
    return (childHeight - height) <= 1
}

fun String.mapToElevation(): String {
    if (equals("S")) return "a"
    return if (equals("E")) "z"
    else this
}

data class Node(val index: Int, val value: String, val adjacentNodes: ArrayList<Node> = arrayListOf()) {
    fun addAdjacentNode(childNode: Node?) {
        if (isAdjacent(this, childNode)) adjacentNodes.add(childNode!!)
    }
}

class PathFinder(val sourceDataGrid: List<Node>) {

    private val nodeCount = sourceDataGrid.size // number of nodes
    private val source = sourceDataGrid.first()
    private val destination = sourceDataGrid.first { it.value == "E" }

    private var queue = ArrayDeque<Node>()
    private var visited = mutableListOf(source)


    val lengths = IntArray(nodeCount)
    private val parents = sourceDataGrid.toMutableList()


    fun run() {
        queue.push(source)
        while (!queue.isEmpty()) {
            val current = queue.pop()
            current.adjacentNodes.forEach {
                if (!visited.contains(it)) {
                    visited.add(it)
                    queue.addLast(it)
                    lengths[it.index] = lengths[current.index] + 1
                    parents[it.index] = current
                }
            }
        }
    }

    fun findShortestPath(): List<Node> {
        return if (!visited.contains(destination)) {
            listOf()
        } else {
            val path = arrayListOf(destination)
            var v: Node = destination
            while (v.index != 0) {
                path.add(parents[v.index])
                v = parents[v.index]
            }
            path.reversed()
        }
    }
}
