package org.vismayb.physfx.logging

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage

import javafx.beans.property.DoubleProperty
import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.LongProperty
import javafx.beans.property.BooleanProperty
import javafx.beans.property.Property

import org.vismayb.physfx.logging.ui.BasePropertyView
import org.vismayb.physfx.logging.ui.BooleanPropertyView
import org.vismayb.physfx.logging.ui.NumberBasePropertyView

class LoggingView : Stage() {
    val root = VBox()
    val loggingScene = Scene(root)
    val logEntries = mutableMapOf<String, BasePropertyView<*>>()

    init {
        title = "Logging"
        width = 800.0
        height = 600.0
        isAlwaysOnTop = true
        scene = loggingScene
    }

    fun addEntry(path: String, value: Property<*>) {
        if(!logEntries.containsKey(path)) {
            // Check if value is a BooleanProperty
            val entryView: BasePropertyView<*> = when (value) {
                is BooleanProperty -> {
                    BooleanPropertyView(path, value.get())
                }
                is DoubleProperty, is IntegerProperty, is FloatProperty, is LongProperty -> {
                    NumberBasePropertyView(path, value.value)
                }
                else -> {
                    throw IllegalArgumentException("Unsupported property type for path: $path")
                }
            }
            logEntries[path] = entryView
            root.children.add(logEntries[path])
        }

        value.addListener { _, _, newValue ->
            logEntries[path]?.updateValue()
        }
    }
}