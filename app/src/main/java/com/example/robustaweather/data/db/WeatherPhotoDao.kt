package com.example.robustaweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.robustaweather.data.db.entity.WeatherPhoto

@Dao
interface WeatherPhotoDao {

    @Query("SELECT * from weather_photo_table")
    fun getWeatherPhotos(): LiveData<List<WeatherPhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherPhoto: WeatherPhoto)

    @Query("DELETE FROM weather_photo_table")
    fun deleteAll()
}