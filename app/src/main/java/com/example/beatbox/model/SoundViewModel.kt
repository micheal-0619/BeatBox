package com.example.beatbox.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.beatbox.manager.Sound

class SoundViewModel : BaseObservable() {
    var sound: Sound? = null
        set(sound) {
            field = sound
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
}