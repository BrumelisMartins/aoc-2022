package day_12

import day_03.alphabet
import readInput
import java.util.*


fun main() {

    val input = readInput("day_12/Day12")
    val testInput = fromRawData(readInput("day_12/Day12_test"))


    fun part1(): Int {
        val finder = PathFinder(fromRawData(input))
        val shortest = finder.findShortestPath()


//        val newlist = finder.sourceDataGrid.map { if (shortest.contains(it)) it.value else "." }
//            .chunked(input.first().length)
//        newlist.forEach {
//            it.forEach { string ->
//                print(string)
//            }
//            println()
//        }
        println(shortest)
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
    return (current.height >= childNode.height - 1)
}

fun String.mapToElevation(): String {
    if (equals("S")) return "a"
    return if (equals("E")) "z"
    else this
}

data class Node(val index: Int, val value: String, val height: Int = alphabet.indexOf(value.mapToElevation())) {
    val adjacentNodes = arrayListOf<Node>()

    fun addAdjacentNode(childNode: Node?) {
        if (isAdjacent(this, childNode)) {
            adjacentNodes.add(childNode!!)
        }
    }
}

data class Step(val node: Node, val distance: Int = 0) {
    fun toward(node: Node): Step =
        Step(node, distance + 1)
}

class PathFinder(sourceDataGrid: List<Node>) {

    private val source = sourceDataGrid.first()
    private val destination = sourceDataGrid.first { it.value == "E" }

    fun findShortestPath(): Int {
        val queue: Queue<Step> = ArrayDeque<Step>().apply { add(Step(source)) }
        val visited = mutableSetOf(source)

        while (!queue.isEmpty()) {
            val current = queue.poll()
            if (current.node == destination) return current.distance
            current.node.adjacentNodes.forEach {
                if (!visited.contains(it)) {
                    visited.add(it)
                    queue.add(current.toward(it))
                }
            }
        }
        return 0
    }

}
