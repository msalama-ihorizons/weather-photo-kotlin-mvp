package com.example.robustaweather.weatherphoto

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.robustaweather.R
import com.example.robustaweather.data.db.WeatherPhotoRoomDatabase
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.data.model.WeatherInfo
import com.example.robustaweather.utils.Utils
import com.example.robustaweather.utils.extensions.toByteArray
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_weather_photo.*
import java.lang.StringBuilder

class WeatherPhotoFragment : Fragment(), WeatherPhotoContract.View {

    private val TAG = "WeatherPhotoFragment"
    private val WEATHER_PHOTO_FILE_NAME = "_weatherPhoto.jpg"

    private var weatherPhotoPresenter: WeatherPhotoPresenter? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var imgPath: String? = null

    companion object {

        private const val EXTRA_IMAGE_PATH = "extraImagePath"

        fun newInstance(imgPath: String?): WeatherPhotoFragment {
            val args: Bundle = Bundle()
            val fragment = WeatherPhotoFragment()
            args.putString(EXTRA_IMAGE_PATH, imgPath)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString(EXTRA_IMAGE_PATH)?.let {
            imgPath = it
        }

        val db = context?.let { WeatherPhotoRoomDatabase.getDatabase(it) }
        weatherPhotoPresenter = WeatherPhotoPresenter(
            view = this,
            weatherPhotoFetcher = WeatherPhotoFetcher(),
            weatherPhotoDao = db?.weatherPhotoDao()
        )

        fusedLocationClient = context?.let { LocationServices.getFusedLocationProviderClient(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgCaptured.setImageBitmap(
            imgPath?.let { Utils.getBitmapFromFilePath(path = it) }
        )

        loadWeatherInfo()
    }

    override fun showWeatherInfo(weatherInfo: WeatherInfo?) {
        Log.e(TAG, weatherInfo?.placeName)

        placeName.text = weatherInfo?.placeName
        tempValue.text = weatherInfo?.temperature?.let {
            Utils.convertFahrenheitToCelsius(fahrenheit = it).toString()
        }

        // take screen shot of fragment and save file path to url
        view?.let {
            val screenShot = Utils.takeScreenShot(view = it)
            val photoFile = Utils.saveImageToSD(
                activity,
                screenShot.toByteArray(),
                StringBuilder().append(System.currentTimeMillis())
                    .append(WEATHER_PHOTO_FILE_NAME).toString()
            )

            weatherPhotoPresenter?.savePhotoToHistory(
                weatherPhoto = WeatherPhoto(photoFile.path)
            )
        }
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun showFailureMessage(errorMessage: String?) {

    }

    private fun loadWeatherInfo() {
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                weatherPhotoPresenter?.loadWeatherInfo(
                    location?.latitude,
                    location?.longitude
                )
            }

    }
}

