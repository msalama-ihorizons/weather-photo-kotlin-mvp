package com.example.robustaweather.weatherphotoslist

import com.example.robustaweather.BaseContract
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.data.model.WeatherInfo

interface WeatherPhotosListContract {

    interface View: BaseContract.View<Any?> {
        fun showPhotosList(
             weatherPhotos: List<WeatherPhoto>?
        )
    }

    interface UserActionsListener {
        fun loadWeatherPhotosList()
    }
}