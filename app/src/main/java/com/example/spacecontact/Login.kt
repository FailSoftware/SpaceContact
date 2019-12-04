package com.example.spacecontact

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.util.*






open class Login : AppCompatActivity() {


    //TODO Si el usuario ya tiene cuenta, hacer que los datos de piloto se carguen directamente desde la base de datos.
    //TODO Si el usuario NO ti ene cuenta, el register deberá llevarle a la pantalla de creacion de personaje y automaticamente aplicar un sprite base, en caso contrario más tarde tendremos problemas de nullexception a la hora de hacer login
    //var pilot: Worker = Worker("Test", 1, 1, 1, 100, null, 2, 100, 100, Worker.Job.PILOT)
    //var usr: User = User(0, false, "Falso", "Falso", "Un usuario", Date(), pilot)

    var c : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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

    /*
    fun prueba (item : MenuItem){

        setLocale("Default value")
        item.setIcon(R.drawable.ic_sett_mute)

    }*/


    fun toMenu(view: View) {

        var name = findViewById<EditText>(R.id.etUser)
        var sName = name.text.toString()

        var pass = findViewById<EditText>(R.id.etPass)
        var sPass = pass.text.toString()

        if (sName.equals("user") && sPass.equals("user")) {
            val i = Intent(this, MainMenu::class.java)
            startActivity(i)

        } else {
            Toast.makeText(this, "No es correcto", Toast.LENGTH_SHORT).show()
        }
    }

    fun toRegister(view: View) {

        val i = Intent(this, Register::class.java)
        startActivity(i)


    }

    fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, Login::class.java)
        finish()
        startActivity(refresh)
    }
}
