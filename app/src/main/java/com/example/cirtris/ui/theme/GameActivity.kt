package com.example.cirtris.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private lateinit var cirtrisView: CirtrisView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cirtrisView = CirtrisView(this)
        setContentView(cirtrisView)
    }

    override fun onResume() {
        super.onResume()
        cirtrisView.resume()
    }

    override fun onPause() {
        super.onPause()
        cirtrisView.pause()
    }
}