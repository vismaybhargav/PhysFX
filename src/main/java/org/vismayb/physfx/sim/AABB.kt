package org.vismayb.physfx.sim

import javafx.scene.shape.Rectangle

class AABB(var minX: Double, var minY: Double, var maxX: Double, var maxY: Double) :
    Rectangle(minX, minY, (maxX - minX).toInt().toDouble(), (maxY - minY).toInt().toDouble()
) {
    fun intersects(other: AABB): Boolean {
        return minX < other.maxX && maxX > other.minX && minY < other.maxY && maxY > other.minY
    }

    fun contains(other: AABB): Boolean {
        return minX < other.minX && maxX > other.maxX && minY < other.minY && maxY > other.maxY
    }
}