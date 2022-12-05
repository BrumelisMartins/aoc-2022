package day_05

fun String.toOperation(): CraneOperation {
    val idMap = Regex("[0-9]+").findAll(this)
        .map(MatchResult::value)
        .toList()
    return CraneOperation(move = idMap[0].toInt(), from = idMap[1].toInt(), to = idMap[2].toInt())
}

fun <T> ArrayList<T>.removeFirstElements(count: Int) {
    for (i in 1..count) {
        if (isNotEmpty()) removeAt(0)
    }
}