package org.toy.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        // lazy를 사용한 늦은 초기화로 sensorManager 객체 만들기. getSystemService를 이용.
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var tiltView: TiltView

    // 센서 정밀도가 변경되면 호출된다
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    // 센서값이 변경되면 호출된다
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x:" +
                    " ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}"
            )
            tiltView.onSensorEvent(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // 슈퍼 클래스의 생성자를 호출하기 전에 다음을 설정하면 디바이스 화면이 가로로 고정.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // 화면이 꺼지지 않게 하기
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
