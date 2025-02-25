package com.example.cirtris

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cirtris.ui.theme.CirtrisView

class MainActivity : AppCompatActivity() {

    private lateinit var cirtrisView: CirtrisView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cirtrisView = CirtrisView(this)
        setContentView(cirtrisView)  // Используем наш SurfaceView
        Log.d("Cirtris", "onCreate called")
    }

    override fun onResume() {
        super.onResume()
        cirtrisView.resume()  // Запускаем игровой поток
        Log.d("Cirtris", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        cirtrisView.pause()  // Останавливаем поток при сворачивании
        Log.d("Cirtris", "onPause called")
    }
}