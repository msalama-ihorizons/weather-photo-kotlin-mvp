package com.example.robustaweather.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>?,
    @SerializedName("main") val main: Main?,
    @SerializedName("wind") val wind: Wind?,
    @SerializedName("timezone") val timezone: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("cod") val cod: Int?
) : Serializable

data class Weather(
    @SerializedName("id") val id: Int?,
    @SerializedName("main") val main: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?
) : Serializable

data class Main(
    @SerializedName("temp") val temp: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("temp_min") val tempMin: Double?,
    @SerializedName("temp_max") val tempMax: Double?,
    @SerializedName("pressure") val pressure: Double?,
    @SerializedName("humidity") val humidity: Int?
) : Serializable

data class Wind(
    @SerializedName("speed") val temp: Double?,
    @SerializedName("deg") val feelsLike: Double?
) : Serializable