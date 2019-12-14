package com.example.spacecontact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.LoadGame
import com.example.spacecontact.gameFunctions.SaveGame
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.NullPointerException

class MainMenu : PrefMenu() {

    lateinit var userM: TextView
    lateinit var usFb: FirebaseUser
    lateinit var fbAu: FirebaseAuth
    lateinit var ship: Ship



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

        // Makes sure you don't get a nullpointer in Characters
        try {
            val lg = LoadGame()
            lg.run()

            ship = lg.ship
        } catch (e: NullPointerException) {
            var nick: String = "";
            val ad : Int = usFb.email!!.indexOf("@")
            if (ad != -1) {
                nick = usFb.email!!.substring(0, ad)
            }
            val w: Worker = Worker(
                nick, 100, 1, 1, 100, null,
                3, 100, 100, Worker.Job.PILOT
            )
            var ship: Ship = Ship(w, this)
            val sg = SaveGame(ship)
            sg.run()
        }



        userM = findViewById(R.id.tvprueba)
        fbAu = FirebaseAuth.getInstance()
        usFb = fbAu.currentUser!!
        userM.text = usFb.email
    }


    fun newGame(view: View) {
        var nick: String = "";
        val ad = usFb.email!!.indexOf("@")
        if (ad != -1) {
            nick = usFb.email!!.substring(0, ad)
        }

        val w: Worker = Worker(
            nick, 100, 1, 1, 100, null,
            3, 100, 100, Worker.Job.PILOT
        )
        var ship: Ship = Ship(w, this)
        val sg = SaveGame(ship)
        sg.run()

        var intent: Intent = Intent(this, Game::class.java)
        startActivity(intent)
    }

    fun continueGame(view: View) {
        val intent = Intent(this, Game::class.java)
        this.startActivity(intent)
    }

    fun editChar(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
        startActivity(intent)
    }


}
