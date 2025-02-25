package com.example.cirtris.utils

import com.example.cirtris.model.Shape
import android.graphics.Color
import java.util.Random

class ShapeGenerator {
    private val random = Random()
    private val colors = listOf(Color.RED, Color.GREEN, Color.BLUE)

    fun generate(): Shape {
        return when (random.nextInt(3)) {
            0 -> createStraight()
            1 -> createSquare()
            else -> createZigZag()
        }
    }

    private fun createSquare(): Shape {
        return Shape(
            mutableListOf(
                4 to 0,
                4 to 1,
                3 to 0,
                3 to 1
            ),
            colors.random()
        )
    }

    private fun createZigZag(): Shape {
        return Shape(
            mutableListOf(
                4 to 0,
                4 to 1,
                3 to 1,
                3 to 2
            ),
            colors.random()
        )
    }

    private fun createStraight(): Shape {
        return Shape(
            mutableListOf(
                4 to 0,  // Outer ring (index 4)
                4 to 1,
                4 to 2,
                4 to 3
            ),
            colors.random()
        )
    }
}