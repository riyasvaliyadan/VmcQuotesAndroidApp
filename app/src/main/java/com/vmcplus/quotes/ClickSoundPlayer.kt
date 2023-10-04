package com.vmcplus.quotes

import android.content.Context
import android.media.MediaPlayer

class ClickSoundPlayer(context: Context) {
    private val mediaPlayer = MediaPlayer.create(context, R.raw.click_sound)

    fun play() {
        mediaPlayer.start()
    }

    fun release() {
        mediaPlayer.release()
    }
}
