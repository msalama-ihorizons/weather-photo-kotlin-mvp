package com.example.robustaweather.weatherphoto

import com.example.robustaweather.OnFinishedListener
import com.example.robustaweather.data.OpenWeatherEndpoints
import com.example.robustaweather.data.ServiceBuilder
import com.example.robustaweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPhotoFetcher {

    fun newInstance() : WeatherPhotoFetcher {
        return WeatherPhotoFetcher()
    }

    fun getWeatherInfo(
        latitude: Double?,
        longitude: Double?,
        listener: OnFinishedListener<WeatherResponse>) {

        val request = ServiceBuilder.ServiceBuilder.buildService(OpenWeatherEndpoints::class.java)
        val call = request.getWeatherByGeoCoordinates(
            lat = latitude.toString(),
            lon = longitude.toString()
        )

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {

                listener.onComplete()

                if (response.isSuccessful){
                    listener.onSuccess(response.body())
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                listener.onComplete()

                listener.onFailure(t.message)
            }
        })

    }
}