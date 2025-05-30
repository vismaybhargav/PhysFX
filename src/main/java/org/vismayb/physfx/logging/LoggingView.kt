package org.vismayb.physfx.logging

import javafx.scene.Scene
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
import org.vismayb.physfx.logging.ui.NumberPropertyView

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
        if(logEntries.containsKey(path))  return

        var entryView: BasePropertyView<*>? = null

        when (value) {
            is DoubleProperty, is IntegerProperty, is FloatProperty, is LongProperty -> {
                entryView = NumberPropertyView(path, value.value)

                value.addListener { _, _, newValue ->
                    (entryView).updateValue(newValue)
                }
            }

            is BooleanProperty -> {
                entryView = BooleanPropertyView(path, value.value)

                value.addListener { _, _, newValue ->
                    (entryView).updateValue(newValue)
                }
            }
        }

        logEntries[path] = entryView ?: return
        root.children.add(logEntries[path])
    }
}