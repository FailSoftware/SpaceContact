package com.example.spacecontact.gameFunctions

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.media.MediaPlayer
import com.example.spacecontact.R


class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

   private var mp= MediaPlayer()


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {


        mp  = MediaPlayer.create(this,
            R.raw.cancionlogin
        )
        mp.setVolume(0.5f,0.5f)
        mp.start()
        return START_STICKY

    }
    override fun onDestroy() {
        super.onDestroy()

        mp.stop()
    }


}
