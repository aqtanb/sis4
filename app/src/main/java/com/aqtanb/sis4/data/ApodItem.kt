package com.aqtanb.sis4.data

import com.google.gson.annotations.SerializedName

data class ApodItem(
    val date: String,
    val title: String,
    val explanation: String,
    val url: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("hdurl")
    val hdUrl: String? = null,
    val copyright: String? = null
)
