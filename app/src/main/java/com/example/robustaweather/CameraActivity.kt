package com.example.robustaweather

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.camerakit.CameraKitView
import com.example.robustaweather.utils.Utils
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CameraActivity : AppCompatActivity() {

    private val CAPTURED_PHOTO_FILE_NAME = "capturedPhoto.jpg"

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, CameraActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnCameraCapture.setOnClickListener {
            cameraView.captureImage { p0, capturedImage ->

                val savedPhoto = Utils.saveImageToSD(
                    this,
                    capturedImage,
                    CAPTURED_PHOTO_FILE_NAME
                )

                // send back captured image uri to weather photo activity
                val resultUri = savedPhoto.toUri();
                val intentResult = Intent()
                intentResult.data = resultUri
                setResult(Activity.RESULT_OK, intentResult);
                finish()
            }
        }

    }

    override fun onStart() {
        cameraView.onStart()
        super.onStart()
    }

    override fun onResume() {
        cameraView.onResume()
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        cameraView.onStop()
    }

    override fun onPause() {
        super.onPause()
        cameraView.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraView.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
