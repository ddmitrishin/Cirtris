package com.example.cirtris.data

import android.content.Context

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("CirtrisPrefs", Context.MODE_PRIVATE)

    fun saveHighScore(score: Int) {
        prefs.edit().putInt("high_score", score).apply()
    }

    fun getHighScore(): Int = prefs.getInt("high_score", 0)
}