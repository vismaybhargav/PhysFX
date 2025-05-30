package org.vismayb.physfx

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import org.vismayb.physfx.logging.LoggingView
import org.vismayb.physfx.math.Vector2D
import org.vismayb.physfx.sim.SimConstants
import org.vismayb.physfx.sim.body.Body
import org.vismayb.physfx.view.Sprite
import org.vismayb.physfx.view.world.ShapedWorld

fun main(args: Array<String>) {
   Driver().main(args)
}

class Driver : Application() {
    fun main(args: Array<String>) {
        launch(*args) // cool spread operator
    }

    override fun start(primaryStage: Stage) {
        primaryStage.width = SimConstants.WINDOW_WIDTH.toDouble()
        primaryStage.height = SimConstants.WINDOW_HEIGHT.toDouble()


        //== WORLD CODE ==//

        val body1 = Body(
            Vector2D(SimConstants.WINDOW_WIDTH / 2.0 - 50 / 2, SimConstants.WINDOW_HEIGHT / 2.0 - 50/2),
            50.0,
            50.0,
            10.0
        )

        //val frictionForce = Force(0.30 * body1.mass * SimConstants.GRAVITY, 180.0)

        val sprite1 = Sprite(body1.position.x, body1.position.y, 50.0, 50.0, Color.color(1.0, 0.0, 0.0, 0.5), true, Color.RED)

        val world = ShapedWorld(
            mapOf(
                body1 to sprite1
            )
        )

        val bgRect = Rectangle(
            0.0,
            0.0,
            SimConstants.WINDOW_WIDTH.toDouble(),
            SimConstants.WINDOW_HEIGHT.toDouble()
        )

        bgRect.fill = Color.color(0.0, 0.0, 0.0, 0.3)

        world.children.add(bgRect)

        val scene = Scene(world)

        scene.onKeyTyped = EventHandler { event ->
            when (event.character) {
                " " -> world.toggle()
                "b" -> {
                    body1.addForce(0.5, 20.0)
                }
                else -> println("Key Pressed: ${event.code}")
            }
        }

        val logging = LoggingView() // TODO: This will become a singleton later

        // Logging code is as easy as adding a listener to a property,
        // no immediate mode logging !
        logging.addEntry("X",world.bodyPosX)
        logging.addEntry("Y",world.bodyPosY)
        logging.addEntry("Running", world.running)

        logging.show() // Show the logging window

        primaryStage.scene = scene
        primaryStage.show()
    }
}
