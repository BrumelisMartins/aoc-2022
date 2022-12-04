package day_02

val gestureList = Gesture.values()

fun String.toGestureFromCode(elfGesture: Gesture?): Gesture? {
    return when (this) {
        "X" -> {
            //lose
            gestureList.find { it.worseThan == elfGesture?.id }
        }
        "Z" -> {
            //win
            gestureList.find { it.betterThan == elfGesture?.id }
        }
        else -> {
            //draw
            elfGesture
        }
    }
}

fun String.toGesture(): Gesture? {
    return when (this) {
        "A" -> Gesture.ROCK
        "B" -> Gesture.PAPER
        "C" -> Gesture.SCISSORS
        "X" -> Gesture.ROCK
        "Y" -> Gesture.PAPER
        "Z" -> Gesture.SCISSORS
        else -> null
    }
}