package day_10

data class CPUOutput(val command: CPUCommand, val value: Int) {
    companion object {
        fun fromString(rawValue: String): CPUOutput {
            val command = CPUCommand.valueOf(rawValue.substringBefore(" ")
                .uppercase())
            val value = if (command == CPUCommand.ADDX) rawValue.substringAfter(" ")
                .toInt() else 0
            return CPUOutput(command, value)
        }
    }
}