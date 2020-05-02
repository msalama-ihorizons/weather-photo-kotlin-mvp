package com.example.robustaweather.weatherphotoslist

import androidx.lifecycle.Observer
import com.example.robustaweather.OnFinishedListener
import com.example.robustaweather.data.db.WeatherPhotoDao
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.data.model.WeatherInfo
import com.example.robustaweather.data.model.WeatherResponse

class WeatherPhotosListPresenter(
    view: WeatherPhotosListContract.View?,
    weatherPhotoDao: WeatherPhotoDao?): WeatherPhotosListContract.UserActionsListener {
    
    private var view: WeatherPhotosListContract.View? = null
    private var weatherPhotoDao: WeatherPhotoDao? = null

    init {
        this.view = view
        this.weatherPhotoDao = weatherPhotoDao
    }

    override fun loadWeatherPhotosList() {
        weatherPhotoDao?.getWeatherPhotos()?.observeForever {
            view?.showPhotosList(weatherPhotos = it)
        }
    }
}