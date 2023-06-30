package com.example.beatbox.manager

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {
    val sounds: List<Sound>

    private val soundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()

    init {
        sounds = loadSounds()
    }

    /*
    * 播放音频
    * 检查通过后， 就可以调用SoundPool.play(Int, Float, Float, Int, Int, Float)函数播放音频了。
    * 这些参数依次是： 音频ID、 左音量、 右音量、 优先级（无效） 、 是否循环和播放速率。 我们需要最大音量
    * 和常速播放， 所以传入值1.0。 是否循环参数传入0， 代表不循环。 （如果想无限循环， 可以传入-1。 相信
    * 这会超级讨人厌。 ）
    * */
    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    private fun loadSounds(): List<Sound> {
        var soundNames: Array<String>
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Cound not load sound $filename", ioe)
            }
        }
        return sounds
    }

    private fun load(sound: Sound) {
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}