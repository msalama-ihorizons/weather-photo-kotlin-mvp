package com.example.robustaweather.utils.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap?.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this?.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    return stream.toByteArray()
}