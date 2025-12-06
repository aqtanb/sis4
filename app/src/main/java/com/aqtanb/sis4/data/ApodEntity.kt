package com.aqtanb.sis4.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod_items")
data class ApodEntity(
    @PrimaryKey val date: String,
    val title: String,
    val explanation: String,
    val url: String,
    val mediaType: String,
    val hdUrl: String?,
    val copyright: String?
)

fun ApodItem.toEntity(): ApodEntity = ApodEntity(
    date = date,
    title = title,
    explanation = explanation,
    url = url,
    mediaType = mediaType,
    hdUrl = hdUrl,
    copyright = copyright
)

fun ApodEntity.toModel(): ApodItem = ApodItem(
    date = date,
    title = title,
    explanation = explanation,
    url = url,
    mediaType = mediaType,
    hdUrl = hdUrl,
    copyright = copyright
)
