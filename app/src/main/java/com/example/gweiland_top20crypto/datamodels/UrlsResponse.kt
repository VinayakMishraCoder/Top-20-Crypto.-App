package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsResponse (
    @SerializedName("website")
    val website: ArrayList<String>? = null
): Parcelable