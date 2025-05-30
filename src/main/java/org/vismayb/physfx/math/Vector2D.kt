package org.vismayb.physfx.math

import kotlin.math.sqrt

class Vector2D(var x: Double, var y: Double) {
    operator fun plus(other: Vector2D): Vector2D {
        return Vector2D(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector2D): Vector2D {
        return Vector2D(x - other.x, y - other.y)
    }

    operator fun plusAssign(other: Vector2D) {
        x += other.x
        y += other.y
    }

    operator fun minusAssign(other: Vector2D) {
        x -= other.x
        y -= other.y
    }

    fun magnitude(): Double {
        return sqrt((x * x) + (y * y))
    }

    fun magnitudeSq(): Double {
        return x * x + y * y
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

