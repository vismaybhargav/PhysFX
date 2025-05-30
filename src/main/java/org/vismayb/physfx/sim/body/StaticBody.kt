package org.vismayb.physfx.sim.body

import org.vismayb.physfx.math.Vector2D
import org.vismayb.physfx.sim.body.features.Collidable

class StaticBody(
    val initialPosition: Vector2D,
    width: Double = 0.0,
    height: Double = 0.0
) : Body(initialPosition, width, height, Double.POSITIVE_INFINITY), Collidable {

    override var position: Vector2D = initialPosition

    override fun intersects(other: Collidable): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCollision(other: Collidable) {
        TODO("Not yet implemented")
    }
}