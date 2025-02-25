import android.os.Parcel
import android.os.Parcelable

data class Shape(
    val blocks: List<Pair<Int, Int>> = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        blocks = parcel.createTypedArrayList(Pair.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(blocks)
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