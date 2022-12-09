package day_09

import java.awt.Point
import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.properties.Delegates

class RopeKnot {

    private var position: Point by Delegates.observable(Point(0, 0)) { _, _, newValue ->
        observer?.onMovementReceived(newValue)
        movementHistory.add(position)
    }
    val movementHistory = mutableSetOf(position)

    private var observer: RopeKnot? = null

    fun attachObserver(observablePoint: RopeKnot) {
        observer = observablePoint
    }

    fun forceRopeAction(action: RopeAction) {
        position = Point(action.movement.x + position.x, action.movement.y + position.y)
    }

    private fun onMovementReceived(newPosition: Point) {
        if (isPointAdjacent(newPosition)) return
        position = getNextPosition(newPosition)
    }

    private fun getNextPosition(newPosition: Point): Point {
        val deltaX = newPosition.x - position.x
        val deltaY = newPosition.y - position.y
        val x = if (deltaX.absoluteValue >= deltaY.absoluteValue) newPosition.x - deltaX.sign else newPosition.x
        val y = if (deltaX.absoluteValue <= deltaY.absoluteValue) newPosition.y - deltaY.sign else newPosition.y
        return Point(x, y)
    }

    private fun isPointAdjacent(point: Point): Boolean =
        point.distance(position) < 2

}