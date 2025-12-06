package com.aqtanb.sis4.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ApodDao {
    @Query("SELECT * FROM apod_items ORDER BY date DESC")
    suspend fun getAll(): List<ApodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ApodEntity>)

    @Query("DELETE FROM apod_items")
    suspend fun clearAll()

    @Transaction
    suspend fun replaceAll(items: List<ApodEntity>) {
        clearAll()
        insertAll(items)
    }
}
