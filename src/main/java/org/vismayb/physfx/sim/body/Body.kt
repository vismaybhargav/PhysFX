package org.vismayb.physfx.sim.body

import org.vismayb.physfx.math.Vector2D
import org.vismayb.physfx.math.degToRad
import org.vismayb.physfx.sim.AABB
import org.vismayb.physfx.sim.SimConstants
import org.vismayb.physfx.sim.unit.Force
import java.util.UUID
import kotlin.math.cos
import kotlin.math.sin

open class Body(
    initialPosition: Vector2D,
    val width: Double,
    val height: Double,
    val mass: Double
) {
    val id: UUID = UUID.randomUUID()
    val aabb: AABB = AABB(initialPosition.x, initialPosition.y, initialPosition.x + width, initialPosition.y + height)

    open var position:     Vector2D = initialPosition
    var acceleration: Vector2D = Vector2D(0.0, 0.0)
    var velocity:     Vector2D = Vector2D(0.0, 0.0)
    var netForce:     Vector2D = Vector2D(0.0, 0.0)
    var momentum:     Vector2D = Vector2D(0.0, 0.0)

    init {
        start()
    }

    /**
     * Adds a force to the body
     * @param magnitude magnitude
     * @param angleInDeg angle in degrees
     */
    fun addForce(magnitude: Double, angleInDeg: Double) {
        netForce.y += magnitude * (sin(degToRad(angleInDeg)))
        netForce.x += magnitude * (cos(degToRad(angleInDeg)))
        acceleration.y = netForce.y / mass
        acceleration.x = netForce.x / mass
    }

    /**
     * Adds a force to the body
     * @param force force in Force class
     */
    fun addForce(force: Force) = addForce(force.magnitude, force.angleInDeg)

    private fun start() {}

    fun update(dt: Double) {
        // make a dy and a ddy so that it gets faster as it falls down
        acceleration.y = if (!isOnGround()) SimConstants.GRAVITY * dt else 0.0
        velocity = velocity + acceleration
        position = position + velocity

        /*
        if(!isOnGround()) {
            position.y += dy
        }
         */

        updateAABB()
    }

    private fun updateAABB() {
        aabb.minX = position.x
        aabb.minY = position.y
        aabb.maxX = position.x + width
        aabb.maxY = position.y + height
    }

    fun isOnGround(): Boolean {
        return position.y >= SimConstants.WINDOW_HEIGHT - height //TODO: Update this for dynamic ground collisions
    }

    override fun equals(other: Any?): Boolean {
        return (other is Body && other.id == id)
    }
}
