package org.vismayb.physfx.view.world

import javafx.animation.AnimationTimer
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Group
import org.vismayb.physfx.sim.body.Body

abstract class AbstractWorld(bodies: List<Body>) : Group() {
    val timer: AnimationTimer = object : AnimationTimer() {
        override fun handle(now: Long) {
            update(now)
            bodies.forEach { it.update(1 / 60.0) }
        }
    }
    var running = SimpleBooleanProperty(false)

    abstract fun update(now: Long)

    fun run() {
        timer.start()
        running.set(true)
    }

    fun toggle() {
        if (running.get()) stop() else run()
    }

    fun stop() {
        timer.stop()
        running.set(false)
    }
}
