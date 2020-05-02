package com.example.robustaweather.weatherphoto

import android.os.AsyncTask
import com.example.robustaweather.OnFinishedListener
import com.example.robustaweather.data.db.WeatherPhotoDao
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.data.model.WeatherInfo
import com.example.robustaweather.data.model.WeatherResponse


class WeatherPhotoPresenter(
    view: WeatherPhotoContract.View?,
    weatherPhotoFetcher: WeatherPhotoFetcher?,
    weatherPhotoDao: WeatherPhotoDao?) : WeatherPhotoContract.UserActionsListener {

    private var view: WeatherPhotoContract.View? = null
    private var weatherPhotoFetcher: WeatherPhotoFetcher? = null
    private var weatherPhotoDao: WeatherPhotoDao? = null

    init {
        this.view = view
        this.weatherPhotoFetcher = weatherPhotoFetcher
        this.weatherPhotoDao = weatherPhotoDao
    }


    override fun loadWeatherInfo(latitude: Double?, longitude: Double?) {
        view?.showProgress()

        weatherPhotoFetcher?.getWeatherInfo(
            latitude,
            longitude,
            object : OnFinishedListener<WeatherResponse> {
                override fun onSuccess(data: WeatherResponse?) {
                    view?.showWeatherInfo(
                        WeatherInfo(
                            placeName = data?.name,
                            temperature = data?.main?.temp,
                            weatherIconCondition = data?.weather?.first()?.icon
                        )
                    )
                }

                override fun onFailure(errorMessage: String?) {
                    view?.showFailureMessage(errorMessage)
                }

                override fun onComplete() {
                    view?.hideProgress()
                }

            }
        )
    }

    override fun savePhotoToHistory(weatherPhoto: WeatherPhoto) {
        AsyncTask.execute {
            weatherPhotoDao?.insert(weatherPhoto)
        }
    }
}