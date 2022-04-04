package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_CAMERA = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val cameraPermission: String? = null

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            shootButton.setOnClickListener(View.OnClickListener {


                checkPermission()
            })
        }
    }

    private fun checkPermission() {
        val permission = Manifest.permission.CAMERA
        val resultCode = ContextCompat.checkSelfPermission(this, permission)
        if (resultCode == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun showCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun requestCameraPermission() {
        val permissions = arrayOf(cameraPermission)
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_CAMERA)
    }

}