package org.vismayb.physfx.logging.ui

import javafx.scene.control.Label

class NumberBasePropertyView(path: String, value: Number) : BasePropertyView<Number>(path, value) {
    private var valueLabel: Label = Label(value.toString())

    init {
        children.add(valueLabel)
    }

    override fun updateValue(newValue: Number) {
        super.updateValue(newValue)
        valueLabel.text = newValue.toString()
    }
}
