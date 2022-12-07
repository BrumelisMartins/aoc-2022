package day_07

sealed interface SystemOutput

sealed class SystemCommand : SystemOutput {
    object ReadContent : SystemCommand()
    object MoveUp : SystemCommand()
    class MoveInto(val directoryName: String) : SystemCommand()
}

sealed class SystemFile(val name: String) : SystemOutput {
    class File(name: String, val size: Int) : SystemFile(name)
    class Directory(name: String, val contents: ArrayList<SystemFile> = arrayListOf()) : SystemFile(name)
}

val SystemFile.allFiles: Sequence<SystemFile>
    get() = sequence {
        yield(this@allFiles)
        if (this@allFiles is SystemFile.Directory) {
            contents.forEach {
                yieldAll(it.allFiles)
            }
        }
    }

val SystemFile.Directory.size: Int
    get() = allFiles.sumOf { if (it is SystemFile.File) it.size else 0 }