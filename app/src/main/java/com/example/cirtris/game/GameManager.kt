package com.example.cirtris.game

import Shape
import com.example.cirtris.utils.ShapeGenerator

class GameManager(
    private val gameField: GameField = GameField(),
    private val shapeGenerator: ShapeGenerator = ShapeGenerator()
) {
    var currentShape: Shape = shapeGenerator.generate()
    var nextShape: Shape = shapeGenerator.generate()
    var score: Int = 0
    var level: Int = 1
    var gameOver: Boolean = false

    private val rings: Int get() = gameField.rings
    private val sectors: Int get() = gameField.sectors

    fun update() {
        val tempShape = currentShape.copy()
        var canMove = true

        // Проверка границ при движении вниз (к центру)
        tempShape.blocks.forEachIndexed { i, (ring, sector) ->
            val newRing = ring - 1
            if (newRing < 0) { // Фигура достигла центра
                canMove = false
                return@forEachIndexed
            }
            tempShape.blocks[i] = newRing to sector
        }

        if (canMove && !gameField.isCollision(tempShape)) {
            currentShape = tempShape
        } else {
            gameField.placeShape(currentShape)
            val cleared = gameField.clearFullRings()
            score += cleared * 100 * level
            level = (score / 1000) + 1
            currentShape = nextShape
            nextShape = shapeGenerator.generate()
            gameOver = gameField.isCollision(currentShape)
        }
    }

    fun moveShape(direction: Direction) {
        val tempShape = currentShape.copy()
        var isValid = true

        when (direction) {
            Direction.LEFT -> {
                tempShape.blocks.forEachIndexed { i, (ring, sector) ->
                    val newSector = (sector - 1 + sectors) % sectors
                    tempShape.blocks[i] = ring to newSector
                }
            }
            Direction.RIGHT -> {
                tempShape.blocks.forEachIndexed { i, (ring, sector) ->
                    val newSector = (sector + 1) % sectors
                    tempShape.blocks[i] = ring to newSector
                }
            }
            Direction.INWARD -> {
                tempShape.blocks.forEachIndexed { i, (ring, sector) ->
                    val newRing = ring - 1
                    if (newRing < 0) isValid = false
                    tempShape.blocks[i] = newRing to sector
                }
            }
            Direction.OUTWARD -> {
                tempShape.blocks.forEachIndexed { i, (ring, sector) ->
                    val newRing = ring + 1
                    if (newRing >= rings) isValid = false
                    tempShape.blocks[i] = newRing to sector
                }
            }
        }

        if (isValid && !gameField.isCollision(tempShape)) {
            currentShape = tempShape
        }
    }
}