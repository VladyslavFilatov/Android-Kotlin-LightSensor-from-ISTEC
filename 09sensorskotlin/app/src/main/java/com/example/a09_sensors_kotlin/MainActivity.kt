package com.example.a09_sensors_kotlin

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null

    var isWorking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display_img.visibility = View.INVISIBLE

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        
    }

    override fun onSensorChanged(event: SensorEvent?) {

        try {
            if(event!!.values[0] < 20000 &&  isWorking == false
            ) {
                isWorking = true
                display_img.visibility = View.VISIBLE
            }else{
                isWorking = false
                display_img.visibility = View.INVISIBLE
            }

        }catch (e:IOException) {

        }



    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // não vamos utilizar
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }


}