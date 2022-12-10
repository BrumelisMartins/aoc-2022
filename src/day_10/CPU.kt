package day_10

class CPU(val data: List<CPUOutput>) {
    private val listOfCycleBreaks = listOf(20, 60, 100, 140, 180, 220)
    private val signalStrengths = mutableListOf<Int>()

    val sumOfSignalStrengths: Int
        get() = signalStrengths.sumOf { it }

    init {
        runCPUCycles()
    }

    private fun runCPUCycles() {
        var cycleCount = 0
        var pixelLocation = 0
        var xValue = 1
        data.forEach {
            val spritePosition = (xValue - 1).. (xValue + 1)

            repeat(it.command.cycleCount) {
                val message = if (pixelLocation in spritePosition) "#" else "."
                print(message)
                cycleCount++
                pixelLocation++
                if (listOfCycleBreaks.contains(cycleCount)) {
                    signalStrengths.add(cycleCount * xValue)
                }
                if (pixelLocation == 40){
                    println()
                    pixelLocation = 0
                }
            }
            xValue += it.value
        }
    }

}