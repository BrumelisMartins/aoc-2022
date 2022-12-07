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
        return substringBefore(" ") == "dir"
    }

    private fun String.toFile(): SystemFile.File {
        return SystemFile.File(substringAfter(" "), substringBefore(" ").toInt())
    }

    private fun String.toDirectory(): SystemFile.Directory {
        return SystemFile.Directory(substringAfter(" "))
    }

    private fun String.toSystemCommand(): SystemCommand {
        return when (this) {
            "\$ cd .." -> SystemCommand.MoveUp
            "\$ ls" -> SystemCommand.ReadContent
            "\$ cd /" -> SystemCommand.RootDirectory
            else -> {
                SystemCommand.MoveInto(substringAfter("cd "))
            }
        }
    }

}