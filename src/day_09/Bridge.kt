package day_09

class Bridge(private val actions: List<RopeAction>) {

    companion object {
        fun fromRawData(rawData: List<String>): Bridge {
            val data = rawData.map { it.toRopeActionList() }
                .flatten()
            return Bridge(data)
        }

        private fun String.toRopeActionList(): List<RopeAction> {
            val repetitions = substringAfter(" ").toInt()
            val action = RopeAction.valueOf(substringBefore(" "))
            return List(repetitions) { action }
        }
    }

    private val tail = RopeKnot()
    private val head = RopeKnot().apply {
        attachObserver(tail)
    }

    private val rope = List(10) { RopeKnot() }.apply {
        forEachIndexed { index, observablePoint ->
            if (index >= size - 1) return@forEachIndexed
            observablePoint.attachObserver(get(index + 1))
        }
    }

    val tailStepCount get() = tail.movementHistory.size
    val ropeStepCount get() = rope.last().movementHistory.size

    fun runMovementSequence() {
        actions.forEach {
            moveHead(it)
            moveRope(it)
        }
    }

    private fun moveHead(ropeAction: RopeAction) {
        head.forceRopeAction(ropeAction)
    }

    private fun moveRope(ropeAction: RopeAction) {
        rope.first().forceRopeAction(ropeAction)
    }

}