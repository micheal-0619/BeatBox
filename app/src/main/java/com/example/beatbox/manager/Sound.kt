package com.example.beatbox.manager

private const val WAV = ".wav"

class Sound(val assetPath: String) {
    val name = assetPath.split("/").last().removeSuffix(WAV)
}