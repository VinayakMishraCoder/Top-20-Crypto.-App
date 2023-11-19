package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingsResponse(
    @SerializedName("data")
    var responseData: ArrayList<ListingItem>? = null,

    @SerializedName("status")
    val status: ResponseStatus? = null
) : Parcelable
