package org.vismayb.physfx.view

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Sprite(
    x: Double = 0.0,
    y: Double = 0.0,
    width: Double = 0.0,
    height: Double = 0.0,
    color: Color = Color.BLACK,
    val bordered: Boolean = false,
    borderColor: Color = Color.BLACK,
) : Rectangle(x, y, width, height) {
    init {
        fill = color
        if (bordered) {
            stroke = borderColor
        }
    }
}
