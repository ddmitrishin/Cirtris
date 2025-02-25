package com.example.cirtris

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cirtris.game.GameField
import com.example.cirtris.ui.theme.CirtrisView

class MainActivity : AppCompatActivity() {
    private lateinit var cirtrisView: CirtrisView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize GameField
        val gameField = GameField(rings = 5, sectors = 10)

        // Create and set the CirtrisView as the content view
        cirtrisView = CirtrisView(this, null, gameField)
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