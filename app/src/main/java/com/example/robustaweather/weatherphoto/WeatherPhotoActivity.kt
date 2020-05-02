package com.example.robustaweather.weatherphoto

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.robustaweather.CameraActivity
import com.example.robustaweather.R

class WeatherPhotoActivity: AppCompatActivity() {

    companion object {
        fun newIntent(context: Context?, photoUri: Uri?): Intent {
            val intent = Intent(context, WeatherPhotoActivity::class.java)
            intent.data = photoUri
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_photo)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.weatherPhotoFrameLayout,
               WeatherPhotoFragment.newInstance(
                   intent.data?.path
               ), WeatherPhotoFragment::class.java.name
            )
            .commit()

    }
}