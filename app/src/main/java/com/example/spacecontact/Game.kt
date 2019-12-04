package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.GridView
import android.widget.Toast
import com.example.spacecontact.entity.Ship
import java.util.*
import kotlin.collections.ArrayList

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var gridLay : GridView = findViewById(R.id.gridLay)
        val ship : Ship = Ship(this)

        var adapter: Adapter = Adapter(this, (ship.crew).toCollection(ArrayList()))
        gridLay.adapter = adapter;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflate : MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.settSpa -> setLocale("es")            //prueba(item)
            R.id.settEng -> setLocale("Default value")
            R.id.settMute -> Toast.makeText(this,"Sound muted...", Toast.LENGTH_SHORT).show()
            R.id.settUnmute -> Toast.makeText(this,"Sound unmuted...", Toast.LENGTH_SHORT).show()
        }


        return super.onOptionsItemSelected(item)
    }


    fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, Game::class.java)
        finish()
        startActivity(refresh)
    }


}