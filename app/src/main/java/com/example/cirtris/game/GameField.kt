package com.example.cirtris.game

import android.util.Log
import com.example.cirtris.model.Shape

class GameField(
    val rings: Int,
    val sectors: Int
) {
    private val field: Array<BooleanArray> = Array(rings) { BooleanArray(sectors) }

    fun placeShape(shape: Shape) {
        for ((ring, sector) in shape.blocks) {
            if (ring < 0 || ring >= rings || sector < 0 || sector >= sectors) {
                throw IllegalArgumentException("Invalid coordinates: ($ring, $sector)")
            }
            field[ring][sector] = true
        }
    }

    fun clearFullRings(): Int {
        var cleared = 0
        for (ring in field.indices) {
            if (field[ring].all { it }) {
                cleared++
                field[ring] = BooleanArray(sectors)
            }
        }
        return cleared
    }

    fun isCollision(shape: Shape): Boolean {
        return shape.blocks.any { (ring, sector) ->
            ring < 0 || ring >= rings || sector < 0 || sector >= sectors || field[ring][sector]
        }
    }
}