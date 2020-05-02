package com.example.robustaweather.weatherphotoslist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.robustaweather.R
import com.example.robustaweather.data.db.entity.WeatherPhoto
import com.example.robustaweather.utils.Utils
import com.leodroidcoder.genericadapter.BaseViewHolder
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.weather_photo_item.view.*

class WeatherPhotosAdapter(context: Context?, listener: OnRecyclerItemClickListener?) :
    GenericRecyclerViewAdapter<
            WeatherPhoto,
            OnRecyclerItemClickListener,
            WeatherPhotosAdapter.WeatherPhotoViewHolder>(context, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherPhotoViewHolder {
        return WeatherPhotoViewHolder(inflate(R.layout.weather_photo_item, parent), listener)
    }

    inner class WeatherPhotoViewHolder(
        itemView: View,
        listener: OnRecyclerItemClickListener?
    ) : BaseViewHolder<WeatherPhoto, OnRecyclerItemClickListener>(itemView, listener),
        View.OnClickListener {

        override fun onBind(item: WeatherPhoto?) {
            itemView.weatherPhoto.setImageBitmap(
                item?.path?.let {
                    Utils.getBitmapFromFilePath(
                        path = it
                    )
                }
            )
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }

    }
}