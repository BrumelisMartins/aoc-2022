package day_07

import java.util.ArrayDeque

class DirectoryController {

    private val totalSpace = 70000000
    private val hardDrive = SystemFile.Directory("root")
    private val currentDirectory = ArrayDeque(listOf(hardDrive))

    val directorySizes = hardDrive.allFiles.filterIsInstance<SystemFile.Directory>()
        .map { (it).size }
    val freeSpace get() = totalSpace - hardDrive.size

    fun handleSystemOutput(output: SystemOutput) {
        when (output) {
            is SystemCommand -> {
                handleSystemCommand(output)
            }
            is SystemFile -> {
                handleSystemFile(output)
            }
        }
    }

    private fun handleSystemFile(systemFile: SystemFile) {
        currentDirectory.first.contents.add(systemFile)
    }

    private fun handleSystemCommand(command: SystemCommand) {
        when (command) {
            is SystemCommand.MoveInto -> {
                val childDirectory = currentDirectory.first.contents.filterIsInstance<SystemFile.Directory>()
                    .first { it.name == command.directoryName }
                childDirectory.let {
                    currentDirectory.push(it)
                }
            }
            SystemCommand.MoveUp -> currentDirectory.pop()
            SystemCommand.ReadContent, SystemCommand.RootDirectory -> {
                //do nothing
            }
        }
    }
}