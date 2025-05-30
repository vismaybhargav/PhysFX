package org.vismayb.physfx.view.world

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import org.vismayb.physfx.sim.SimConstants
import org.vismayb.physfx.sim.body.Body
import org.vismayb.physfx.view.Sprite

class ShapedWorld(val bodies: Map<Body, Sprite>) : AbstractWorld(bodies.keys.toList()) {
    var bodyPosX: SimpleDoubleProperty = SimpleDoubleProperty(bodies.keys.first().position.x)
    var bodyPosY: SimpleDoubleProperty = SimpleDoubleProperty(bodies.keys.first().position.y)

    init {
        bodies.forEach { _, sprite ->
            children.add(sprite)
        }
        val circle = Circle(SimConstants.WINDOW_WIDTH / 2.0, SimConstants.WINDOW_HEIGHT / 2.0, 3.0, Color.RED)
        children.add(circle)
    }

    override fun update(now: Long) {
        bodies.forEach { (body, sprite) ->
            sprite.x = body.aabb.minX
            sprite.y = body.aabb.minY
            bodyPosX.set(body.position.x)
            bodyPosY.set(body.position.y)
        }
    }
}