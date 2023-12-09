package com.example.cooksmart.data

import android.os.Parcel
import android.os.Parcelable

data class Ingredient(val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()?: "")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}
