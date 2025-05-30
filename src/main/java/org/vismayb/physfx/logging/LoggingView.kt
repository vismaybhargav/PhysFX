package org.vismayb.physfx.logging

import javafx.beans.property.Property
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage

class LoggingView : Stage() {
    val root = VBox()
    val loggingScene = Scene(root)
    val logEntries = mutableMapOf<String, Label>()

    init {
        title = "Logging"
        width = 800.0
        height = 600.0
        scene = loggingScene

        // String --> String
    }

    fun addListener(path: String, value: Property<*>) {
        value.addListener { _, _, newValue ->
            if(logEntries.containsKey(path)) {
                println("Updating log entry for $path with new value: $newValue")
                logEntries[path]?.text = "$path: $newValue"
            } else {
                println("Adding new log entry for $path with value: $newValue")
                logEntries[path] = Label("$path: $newValue")
                root.children.add(logEntries[path])
            }
        }
    }
}