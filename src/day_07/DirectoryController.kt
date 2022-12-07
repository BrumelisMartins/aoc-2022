package day_07

import java.util.ArrayDeque

class DirectoryController {

    val totalSpace = 70000000

    val hardDrive = SystemFile.Directory("root")
    private val currentDirectory = ArrayDeque(listOf(hardDrive))

    val directorySizes = hardDrive.allFiles.filter { it is SystemFile.Directory }
        .map { (it as SystemFile.Directory).size }

    fun handleSystemOutput(output: SystemOutput) {
        when (output) {
            is SystemCommand -> {
                if (output is SystemCommand.MoveInto && output.directoryName == "/") return
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
                val childDirectory = currentDirectory.first.contents.first { it.name == command.directoryName && it is SystemFile.Directory }
                childDirectory.let {
                    currentDirectory.push(it as SystemFile.Directory)
                }
            }
            SystemCommand.MoveUp -> currentDirectory.pop()
            SystemCommand.ReadContent -> {
                //do nothing
            }
        }
    }
}