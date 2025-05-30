package org.vismayb.physfx.logging.ui

import javafx.scene.control.Label
import javafx.scene.layout.HBox

abstract class BasePropertyView<T>(path: String, var value: T) : HBox() {
    open val pathLabel = Label(path + ": ")

    init {
        children.add(pathLabel)
    }

    open fun updateValue(newValue: T) {
        value = newValue
    }
}
