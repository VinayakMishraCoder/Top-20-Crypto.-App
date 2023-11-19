package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingItem(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("symbol")
    val symbol: String? = null,

    @SerializedName("slug")
    val slug: String? = null,

    @SerializedName("cmc_rank")
    val cmc_rank: Int? = null,

    @SerializedName("tags")
    val tags: ArrayList<String>? = null,

    @SerializedName("quote")
    val quote: Map<String, QuoteItem>? = null,

    @Transient
    var cryptoInfoItem: CryptoInformationItem? = null

) : Parcelable