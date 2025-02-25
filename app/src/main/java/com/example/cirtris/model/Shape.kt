package com.example.cirtris.model

data class Shape(
    val blocks: MutableList<Pair<Int, Int>>, // (ring, sector)
    val color: Int
) {
    fun rotate(clockwise: Boolean = true) {
        blocks.forEachIndexed { i, (ring, sector) ->
            blocks[i] = if (clockwise)
                Pair(ring, (sector + 1) % 12)
            else
                Pair(ring, (sector - 1 + 12) % 12)
        }
    }
}