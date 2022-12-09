package day_09

import java.awt.Point

enum class RopeAction(val movement: Point) {
    U(Point(0, 1)),
    D(Point(0, -1)),
    R(Point(1, 0)),
    L(Point(-1, 0))
}