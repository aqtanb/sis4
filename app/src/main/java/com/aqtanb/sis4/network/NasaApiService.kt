package com.aqtanb.sis4.network

import com.aqtanb.sis4.data.ApodItem
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getApodItems(
        @Query("count") count: Int,
        @Query("api_key") apiKey: String
    ): List<ApodItem>
}
