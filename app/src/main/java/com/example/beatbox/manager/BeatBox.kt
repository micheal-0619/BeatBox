package com.example.beatbox.manager

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {
    fun loadSounds(): List<String> {
        return try {
            val soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG, "loadSounds: Found ${soundNames.size} sounds ")
            soundNames.asList()
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            emptyList()
        }
    }
}