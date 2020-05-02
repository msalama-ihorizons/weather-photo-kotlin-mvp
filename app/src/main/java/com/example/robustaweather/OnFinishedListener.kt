package com.example.robustaweather

/**
 * Created by mohammed.salama on 9/20/2016.
 */
interface OnFinishedListener<T> {
    fun onSuccess(data: T?)
    fun onFailure(errorMessage: String?)
    fun onComplete()
}