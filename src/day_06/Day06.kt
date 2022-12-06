package day_06

import readWholeFile

const val SMALL_PACKET_SIZE = 4
const val LARGE_PACKET_SIZE = 14

fun main() {
    val input = readWholeFile("day_06/Day06")
    val testInput = readWholeFile("day_06/Day06_test")

    val packet = findPacketIndex(input.toPackets(SMALL_PACKET_SIZE), SMALL_PACKET_SIZE)
    val testPacket = findPacketIndex(testInput.toPackets(SMALL_PACKET_SIZE), SMALL_PACKET_SIZE)
    val largePacket = findPacketIndex(input.toPackets(LARGE_PACKET_SIZE), LARGE_PACKET_SIZE)

    println(packet)
    println(testPacket)
    println(largePacket)
}


fun findPacketIndex(data: List<String>, packetSize: Int): Int {
    data.forEachIndexed { index, packet ->
        if (isPacketReceived(packet, packetSize)) {
            return index + packetSize
        }
    }
    return 0
}

fun isPacketReceived(data: String, packetSize: Int): Boolean {
    return data.chunked(1)
        .distinct().size == packetSize
}