package org.vismayb.physfx.view.world

import javafx.animation.AnimationTimer
import javafx.scene.Group
import org.vismayb.physfx.sim.body.Body

abstract class AbstractWorld(bodies: List<Body>) : Group() {
    val timer: AnimationTimer = object : AnimationTimer() {
        override fun handle(now: Long) {
            update(now)
            bodies.forEach { it.update(1 / 60.0) }
        }
    }
    var running = false

    abstract fun update(now: Long)

    fun run() {
        timer.start()
        running = true
    }

    fun toggle() {
        if (running) stop() else run()
    }

    fun stop() {
        timer.stop()
        running = false
    }
}
