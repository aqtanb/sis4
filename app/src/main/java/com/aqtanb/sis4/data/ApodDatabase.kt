package com.aqtanb.sis4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ApodEntity::class], version = 1, exportSchema = false)
abstract class ApodDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao

    companion object {
        @Volatile
        private var INSTANCE: ApodDatabase? = null

        fun getInstance(context: Context): ApodDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ApodDatabase::class.java,
                    "apod-db"
                ).fallbackToDestructiveMigration().build().also { db ->
                    INSTANCE = db
                }
            }
        }
    }
}
