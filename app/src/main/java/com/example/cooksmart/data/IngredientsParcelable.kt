package com.example.cooksmart.data

import android.os.Parcel
import android.os.Parcelable

data class IngredientsParcelable(
    private val ingredientList: ArrayList<Ingredient>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        ArrayList<Ingredient>().apply {
            parcel.readTypedList(this, Ingredient.CREATOR)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(ingredientList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientsParcelable> {
        override fun createFromParcel(parcel: Parcel): IngredientsParcelable {
            return IngredientsParcelable(parcel)
        }

        override fun newArray(size: Int): Array<IngredientsParcelable?> {
            return arrayOfNulls(size)
        }
    }
}




