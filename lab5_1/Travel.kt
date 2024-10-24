package com.example.lab5_1

import android.os.Parcel
import android.os.Parcelable

data class Travel(
    var id: String = "",
    var place: String = "",
    var dateTravel: String = "",
    var interectingPlaces: List<String> = emptyList(),
    var expirience: List<String> = emptyList()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.createStringArrayList() ?: emptyList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(place)
        parcel.writeString(dateTravel)
        parcel.writeStringList(interectingPlaces)
        parcel.writeStringList(expirience)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Travel> {
        override fun createFromParcel(parcel: Parcel): Travel {
            return Travel(parcel)
        }

        override fun newArray(size: Int): Array<Travel?> {
            return arrayOfNulls(size)
        }
    }
}