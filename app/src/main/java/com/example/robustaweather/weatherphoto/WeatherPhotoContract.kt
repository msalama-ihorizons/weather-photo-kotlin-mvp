package com.example.robustaweather.weatherphoto

import com.example.robustaweather.BaseContract
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.data.model.WeatherInfo

interface WeatherPhotoContract {

    interface View : BaseContract.View<Any?> {
        fun showWeatherInfo(
            weatherInfo: WeatherInfo?
        )
    }

    interface UserActionsListener {
        fun loadWeatherInfo(
            latitude: Double?,
            longitude: Double?
        )

        fun savePhotoToHistory(weatherPhoto: WeatherPhoto)
    }
}