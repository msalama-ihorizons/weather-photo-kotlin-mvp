package com.example.robustaweather.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class Utils {

    companion object {

         fun takeScreenShot(view: View): Bitmap? {
            var bitmap =
                Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            var canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        fun getBitmapFromFilePath(path: String): Bitmap? {
            val imgFile = File(path)
            if (imgFile.exists()) {
                return BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            }
            return null
        }

        fun saveImageToSD(context: Context?, b: ByteArray, fileName: String): File {
            val savedPhoto =
                File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
            try {
                val outputStream =  FileOutputStream(savedPhoto.path)
                outputStream.write(b)
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return savedPhoto
        }

         fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
            return (fahrenheit - 32) * 5 / 9
        }
    }
}