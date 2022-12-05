package day_05

import readInput


fun main() {
    val input = readInput("day_05/Day05")
    val ship = Ship.fromRawValues(input)
    ship.sortShip(false)
    val shipWithImprovedCrane = Ship.fromRawValues(input)
    shipWithImprovedCrane.sortShip(true)

    println(ship.getTopCrates())
    println(shipWithImprovedCrane.getTopCrates())

}