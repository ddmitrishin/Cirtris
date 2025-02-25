package com.example.cirtris.model

import android.os.Parcel
import android.os.Parcelable

data class Shape(
    val blocks: MutableList<Pair<Int, Int>> = mutableListOf(),
    val color: Int // Add a color property
) : Parcelable {
    constructor(parcel: Parcel) : this(
        blocks = mutableListOf<Pair<Int, Int>>().apply {
            val size = parcel.readInt()
            repeat(size) {
                val first = parcel.readInt()
                val second = parcel.readInt()
                add(Pair(first, second))
            }
        },
        color = parcel.readInt() // Read the color from the Parcel
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(blocks.size)
        for ((first, second) in blocks) {
            parcel.writeInt(first)
            parcel.writeInt(second)
        }
        parcel.writeInt(color) // Write the color to the Parcel
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shape> {
        override fun createFromParcel(parcel: Parcel): Shape {
            return Shape(parcel)
        }

        override fun newArray(size: Int): Array<Shape?> {
            return arrayOfNulls(size)
        }
    }
}