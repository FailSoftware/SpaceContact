package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.LoadGame
import com.example.spacecontact.gameFunctions.SaveGame
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.w3c.dom.Text
import java.util.*

class MainMenu : Login(){

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

        var intent = intent
        usr = intent.getParcelableExtra("usr")
        wor = intent.getParcelableExtra("wor")
        usr.pilot = wor

        val lg = LoadGame()
        lg.run()

        ship = lg.ship




        usr.pilot = ship.crew[0]
        
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
        //TODO Change to playership when login is done
        val gson = Gson()
        val json = gson.toJson(ship)
        val intent = Intent(this, Game::class.java)
        intent.putExtra("json", json)
        this.startActivity(intent)
    }

    fun editChar(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
        intent.putExtra("usr", this.usr)
        intent.putExtra("wor", this.wor)
        startActivity(intent)
    }


}
