package com.example.beatbox.model

import com.example.beatbox.manager.Sound

class SoundViewModel {
    var sound: Sound? = null
        set(sound) {
            field = sound
        }
    val title: String?
        get() = sound?.name
}