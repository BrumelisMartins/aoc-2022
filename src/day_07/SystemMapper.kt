package day_07

class SystemMapper {
    fun getData(rawData: List<String>): List<SystemOutput> {
        return rawData.map {
            when {
                it.isCommand() -> it.toSystemCommand()
                it.isDirectory() -> it.toDirectory()
                else -> it.toFile()
            }
        }
    }

    private fun String.isCommand(): Boolean {
        return contains("$")
    }

    private fun String.isDirectory(): Boolean {
        return split(" ").first() == "dir"
    }

    private fun String.toFile(): SystemFile.File {
        val splitString = split(" ")
        return SystemFile.File(splitString.last(), splitString.first()
            .toInt())
    }

    private fun String.toDirectory(): SystemFile.Directory {
        val splitString = split(" ")
        return SystemFile.Directory(splitString.last())
    }

    private fun String.toSystemCommand(): SystemCommand {
        return when (this) {
            "\$ cd .." -> SystemCommand.MoveUp
            "\$ ls" -> SystemCommand.ReadContent
            else -> {
                val directoryName = split(" ").last()
                SystemCommand.MoveInto(directoryName)
            }
        }
    }

}