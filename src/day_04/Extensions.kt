package day_04

fun String.toSectionList(): List<Int> {
    val listOfSections = split('-').map { it.toInt() }
    return (listOfSections.first()..listOfSections.last()).toList()
}

fun String.toGroup(): Group {
    val groupStrings = split(',')
    return Group(groupStrings.first().toSectionList(), groupStrings.last().toSectionList())
}

fun List<String>.toListOfGroups(): List<Group> {
    return map {
        it.toGroup()
    }
}