package com.example.robustaweather.weatherphotoslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.robustaweather.CameraActivity
import com.example.robustaweather.R
import com.example.robustaweather.data.db.WeatherPhotoRoomDatabase
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.weatherphoto.WeatherPhotoActivity
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.weather_photos_list_fragment.*

class WeatherPhotosListFragment : Fragment(), WeatherPhotosListContract.View {

    private val NUMBER_OF_COL = 4
    private val CAPTURE_IMAGE_REQUEST = 1542

    private var weatherPhotoListPresenter: WeatherPhotosListPresenter? = null
    private var weatherPhotosAdapter: WeatherPhotosAdapter? = null

    companion object {
        fun newInstance(): WeatherPhotosListFragment {
            val args: Bundle = Bundle()
            val fragment = WeatherPhotosListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = context?.let { WeatherPhotoRoomDatabase.getDatabase(it) }

        weatherPhotoListPresenter = WeatherPhotosListPresenter(
            view = this,
            weatherPhotoDao = db?.weatherPhotoDao()
        )

        weatherPhotosAdapter = WeatherPhotosAdapter(
            context = activity,
            listener =  OnRecyclerItemClickListener {

            }
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_photos_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabCapturePhoto.setOnClickListener {
            startActivityForResult(
                CameraActivity.newIntent(activity),
                CAPTURE_IMAGE_REQUEST
            )
        }

        weatherRecyclerView.layoutManager = GridLayoutManager(context, NUMBER_OF_COL)
        weatherRecyclerView.adapter = weatherPhotosAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAPTURE_IMAGE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                startActivity(WeatherPhotoActivity.newIntent(activity, data?.data))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        weatherPhotoListPresenter?.loadWeatherPhotosList()
    }

    override fun showPhotosList(weatherPhotos: List<WeatherPhoto>?) {
        weatherPhotosAdapter?.items = weatherPhotos
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun showFailureMessage(errorMessage: String?) {
    }
}