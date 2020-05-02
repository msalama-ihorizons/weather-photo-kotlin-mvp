package com.example.robustaweather.data

import com.example.robustaweather.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  OpenWeatherEndpoints {

    @GET("data/2.5/weather")
    fun getWeatherByGeoCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Call<WeatherResponse>
}