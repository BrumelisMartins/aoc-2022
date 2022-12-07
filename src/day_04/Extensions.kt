package day_04

fun String.toSectionList(): List<Int> {
    val delimiter = '-'
    return (substringBefore(delimiter).toInt()..substringAfter(delimiter).toInt()).toList()
}

fun String.toGroup(): Group {
    val delimiter = ','
    return Group(substringBefore(delimiter).toSectionList(), substringAfter(delimiter).toSectionList())
}

fun List<String>.toListOfGroups(): List<Group> {
    return map {
        it.toGroup()
    }
}