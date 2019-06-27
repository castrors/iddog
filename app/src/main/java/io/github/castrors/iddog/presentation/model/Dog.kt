package io.github.castrors.iddog.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class Dog(val url: String): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dog> {
        override fun createFromParcel(parcel: Parcel): Dog {
            return Dog(parcel)
        }

        override fun newArray(size: Int): Array<Dog?> {
            return arrayOfNulls(size)
        }
    }
}