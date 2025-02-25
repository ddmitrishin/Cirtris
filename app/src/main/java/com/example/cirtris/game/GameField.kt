package com.example.cirtris.game

import com.example.cirtris.model.Shape

class GameField(
    val rings: Int = 5, // Количество колец
    val sectors: Int = 12 // Количество секторов
) {
    val field = Array(rings) { BooleanArray(sectors) } // Поле игры

    fun isCollision(shape: Shape): Boolean {
        return shape.blocks.any { (ring, sector) ->
            ring < 0 || ring >= rings || sector < 0 || sector >= sectors || field[ring][sector]
        }
    }

    fun placeShape(shape: Shape) {
        shape.blocks.forEach { (ring, sector) ->
            if (ring in 0 until rings && sector in 0 until sectors) {
                field[ring][sector] = true
            } else {
                throw IllegalArgumentException("Invalid ring/sector: $ring/$sector")
            }
        }
    }

    fun clearFullRings(): Int {
        var cleared = 0
        for (ring in field.indices) {
            if (field[ring].all { it }) {
                cleared++
                field[ring].fill(false)
            }
        }
        return cleared
    }
}