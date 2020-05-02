package com.example.robustaweather

/**
 * Created by mohammed.salama on 10/25/2016.
 */
interface BaseContract {
    interface View<T> {
        fun hideProgress()
        fun showProgress()
        fun showFailureMessage(errorMessage: String?)
    }
}