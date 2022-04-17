package com.example.flashtap

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private lateinit var camaraM: CameraManager
    private lateinit var powerButton: ImageButton
    var isFlash = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        powerButton = findViewById(R.id.toggle_button )
        camaraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerButton.setOnClickListener { flashLightOnRoOff(it) }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnRoOff(it: View?) {

        if(!isFlash){
            val camerListId = camaraM.cameraIdList[0]
            camaraM.setTorchMode("$camerListId", true)
            isFlash = true
            powerButton.setImageResource(R.drawable.ic_power_on)
            Toast.makeText(this, "Flash Light is On", Toast.LENGTH_LONG).show()
        }else{
            val camerListId = camaraM.cameraIdList[0]
            camaraM.setTorchMode("$camerListId", false)
            isFlash = false
            powerButton.setImageResource(R.drawable.ic_power_off)
            Toast.makeText(this, "Flash Light is Off", Toast.LENGTH_LONG).show()
        }
    }
}