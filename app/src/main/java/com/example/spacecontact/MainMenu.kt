package com.example.spacecontact

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.spacecontact.entity.Ship
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.w3c.dom.Text
import java.io.File
import java.util.*
import java.util.zip.Deflater
import java.util.zip.GZIPInputStream

class MainMenu : Login(){

    lateinit var userM : TextView
    lateinit var usFb : FirebaseUser
    lateinit var fbAu : FirebaseAuth
    lateinit var fbd : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

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
        var ship : Ship = Ship(this)
        val gson = Gson()
        val json : String = gson.toJson(ship)
        val path : File = this.filesDir
        val f = File(path, "./save.json")
        if (!f.exists()) {
            f.createNewFile()
        }
        val fos = this.openFileOutput("save.json", Context.MODE_PRIVATE)
        fos.write(json.toByteArray(), 0, json.length)
        fos.flush()
        fos.close()
        val intent = Intent(this, Game::class.java)

        Log.d("json", json)
        this.startActivity(intent)
    }

    fun editChar(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
        startActivity(intent)
    }


}
