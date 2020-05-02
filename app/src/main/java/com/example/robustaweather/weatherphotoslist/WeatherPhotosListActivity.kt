package com.example.robustaweather.weatherphotoslist

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.robustaweather.R
import pub.devrel.easypermissions.EasyPermissions


class WeatherPhotosListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_photos_list)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.weatherPhotosListFrameLayout,
                WeatherPhotosListFragment.newInstance(), WeatherPhotosListFragment::class.java.name
            )
            .commit()

    }

    override fun onResume() {
        super.onResume()
    }

}