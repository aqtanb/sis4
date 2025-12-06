package com.aqtanb.sis4.data

import com.aqtanb.sis4.network.NasaApiService

data class ApodResult(
    val items: List<ApodItem>,
    val fromCache: Boolean
)

class ApodRepository(
    private val apiService: NasaApiService,
    private val database: ApodDatabase
) {

    private val apodDao = database.apodDao()

    suspend fun loadApods(count: Int, apiKey: String): ApodResult {
        return try {
            val items = apiService.getApodItems(count, apiKey)
            apodDao.replaceAll(items.map { it.toEntity() })
            ApodResult(items, fromCache = false)
        } catch (e: Exception) {
            val cached = apodDao.getAll()
            if (cached.isNotEmpty()) {
                ApodResult(cached.map { it.toModel() }, fromCache = true)
            } else {
                throw e
            }
        }
    }
}
