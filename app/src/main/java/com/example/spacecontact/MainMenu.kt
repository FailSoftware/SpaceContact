package com.example.spacecontact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.LoadGame
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class MainMenu : PrefMenu(){

    lateinit var userM : TextView
    lateinit var usFb : FirebaseUser
    lateinit var fbAu : FirebaseAuth
    lateinit var fbd : FirebaseDatabase
    lateinit var usr : User
    lateinit var ship : Ship
    lateinit var wor : Worker



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

        val lg = LoadGame()
        lg.run()

        ship = lg.ship

        
        userM = findViewById(R.id.tvprueba)
        fbAu = FirebaseAuth.getInstance()
        usFb = fbAu.currentUser!!
        userM.text = usFb.email
    }


    fun newGame(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
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
