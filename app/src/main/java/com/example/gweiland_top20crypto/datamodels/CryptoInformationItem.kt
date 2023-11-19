package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoInformationItem (

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("symbol")
    val symbol: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("slug")
    val slug: String? = null,

    @SerializedName("logo")
    val logo: String? = null,

    @SerializedName("tags")
    val tags: ArrayList<String>? = null,

    @SerializedName("website")
    val urls: UrlsResponse? = null
): Parcelable