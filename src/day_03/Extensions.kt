package day_03

val alphabet = (('a'..'z').toList() + ('A'..'Z').toList()).map { it.toString() }

fun String.toPriority(): Int {
    return alphabet.indexOf(this) + 1
}

fun String.toBag(): Bag {
    val compartmentList = chunked(length / 2) { bunchOfItems: CharSequence -> bunchOfItems.chunked(1) }
    return Bag(compartmentList.first(), compartmentList.last())
}

fun String.toMessyBag(): MessyBag {
    return MessyBag(chunked(1))
}