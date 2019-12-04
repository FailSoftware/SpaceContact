package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.*

class MainMenu : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }


    fun newGame(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
        startActivity(intent)
    }

    fun continueGame(view: View) {
        var intent: Intent = Intent(this, Game::class.java)
        startActivity(intent)
    }

    fun editChar(view: View) {
        var intent: Intent = Intent(this, Characters::class.java)
        startActivity(intent)
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
            R.id.settMute -> {
                //TODO HACER LA FUNCION PARA MUTEAR EL SONIDO Y AÑADIRLO A PREFERENCIAS
                Toast.makeText(this,"Sound muted...", Toast.LENGTH_SHORT).show()}
            R.id.settUnmute -> {
                ////TODO HACER LA FUNCION PARA UNMUTEAR EL SONIDO Y AÑADIRLO A PREFERENCIAS
                Toast.makeText(this,"Sound unmuted...", Toast.LENGTH_SHORT).show()}
            R.id.settContact ->{
                val i = Intent(this, Contact::class.java)
                startActivity(i)
            }
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
        val refresh = Intent(this, MainMenu::class.java)
        finish()
        startActivity(refresh)
    }

    fun rueba(view: View) {

        var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

}
