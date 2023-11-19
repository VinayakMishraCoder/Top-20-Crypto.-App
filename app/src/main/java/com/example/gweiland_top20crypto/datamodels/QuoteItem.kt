package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuoteItem(
    @SerializedName("price")
    val price: Double? = null,

    @SerializedName("volume_24h")
    val volume_24h: Double? = null,

    @SerializedName("percent_change_1h")
    val percent_change_1h: Double? = null,

    @SerializedName("percent_change_24h")
    val percent_change_24h: Double? = null,

    @SerializedName("percent_change_7d")
    val percent_change_7d: Double? = null,

    @SerializedName("market_cap")
    val market_cap: Double? = null,

    @SerializedName("volume_change_24h")
    val volume_change_24h: Double? = null,

    @SerializedName("last_updated")
    val last_updated: String? = null
): Parcelable
