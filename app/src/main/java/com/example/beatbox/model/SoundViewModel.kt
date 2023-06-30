package com.example.beatbox.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.beatbox.manager.BeatBox
import com.example.beatbox.manager.Sound

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {
    var sound: Sound? = null
        set(sound) {
            field = sound
        }

    @get:Bindable
    val title: String?
        get() = sound?.name

    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }
}