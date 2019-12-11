package com.example.spacecontact

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T







class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

   private var mp= MediaPlayer()


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Servicio en Ejecucion", Toast.LENGTH_SHORT).show()

        mp  = MediaPlayer.create(this,R.raw.cancionlogin)
        mp.setVolume(0.5f,0.5f)
        mp.start()
        return START_STICKY

    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Servicio destruido", Toast.LENGTH_SHORT).show()
        mp.stop()
    }


}
