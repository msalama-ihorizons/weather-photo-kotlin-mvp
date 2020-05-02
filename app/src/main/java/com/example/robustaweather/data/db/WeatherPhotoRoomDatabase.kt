package com.example.robustaweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.robustaweather.data.db.entity.WeatherPhoto

@Database(entities = arrayOf(WeatherPhoto::class), version = 1, exportSchema = false)
 abstract class WeatherPhotoRoomDatabase : RoomDatabase() {

    abstract fun weatherPhotoDao(): WeatherPhotoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WeatherPhotoRoomDatabase? = null

        fun getDatabase(context: Context): WeatherPhotoRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherPhotoRoomDatabase::class.java,
                    "weather_photo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}