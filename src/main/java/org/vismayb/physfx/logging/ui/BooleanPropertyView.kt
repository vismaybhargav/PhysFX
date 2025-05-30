package org.vismayb.physfx.logging.ui

import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class BooleanPropertyView(path: String, value: Boolean) : BasePropertyView<Boolean>(path, value) {
    /**
     * A circle that indicates the status of the boolean property.
     * Green for true, red for false.
     */
    private var statusCircle: Circle = Circle(10.0)

    init {
        children.add(statusCircle)
    }

    override fun updateValue(newValue: Boolean) {
         super.updateValue(newValue)
         statusCircle.setFill(if (newValue) Color.GREEN else Color.RED)
    }
}
