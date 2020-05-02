package com.example.robustaweather.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_photo_table")
class WeatherPhoto(@PrimaryKey @ColumnInfo(name = "path") val path: String)