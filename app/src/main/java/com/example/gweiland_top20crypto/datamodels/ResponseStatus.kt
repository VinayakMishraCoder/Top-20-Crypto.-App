package com.example.gweiland_top20crypto.datamodels

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseStatus (
    @SerializedName("error_code")
    val errorCode:Int? = null,

    @SerializedName("error_message")
    val errorMessage:String? = null
): Parcelable