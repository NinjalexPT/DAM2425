package com.example.gametest

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class SoundPlayer(private val context: Context) {

    private val soundPool: SoundPool
    private val soundMap = mutableMapOf<Int, Int>()

    init {
        // Configurando o SoundPool com atributos de áudio
        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }

    // Função para carregar um som a partir de um recurso (R.raw)
    fun loadSound(resourceId: Int): Int {
        val soundId = soundPool.load(context, resourceId, 1)
        soundMap[resourceId] = soundId
        return soundId
    }

    // Função para tocar um som carregado
    fun playSound(resourceId: Int) {
        val soundId = soundMap[resourceId] ?: return
        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    // Função para liberar recursos quando não for mais necessário
    fun release() {
        soundPool.release()
    }
}