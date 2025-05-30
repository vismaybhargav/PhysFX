package org.vismayb.physfx.sim.body.features

interface Collidable {
    /**
     * Checks if this collidable intersects with another collidable.
     *
     * @param other The other collidable to check against.
     * @return True if they intersect, false otherwise.
     */
    fun intersects(other: Collidable): Boolean

    /**
     * What to do when this collidable collides with another collidable.
     *
     * @param other The other collidable that this one has collided with.
     */
    fun onCollision(other: Collidable)
}