package day_06

fun String.toPackets(packetSize: Int): List<String> {
    return windowed(packetSize, 1)
}