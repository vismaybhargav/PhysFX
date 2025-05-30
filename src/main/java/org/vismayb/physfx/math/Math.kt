package org.vismayb.physfx.math

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min

fun degToRad(deg: Double): Double = deg * (PI / 180.0)
fun radToDeg(deg: Double): Double = deg * (180.0 / PI)

fun clamp(value: Double, max: Double, min: Double): Double = min(max, max(value, min))
fun clamp(value: Int,    max: Int,    min: Int   ): Int    = min(max, max(value, min))
fun clamp(value: Float,  max: Float,  min: Float ): Float  = min(max, max(value, min))
