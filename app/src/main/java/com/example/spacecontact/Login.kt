package com.example.spacecontact

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import java.util.*
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.DisplayMetrics
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class Login : AppCompatActivity() {


    //TODO Si el usuario ya tiene cuenta, hacer que los datos de piloto se carguen directamente desde la base de datos.
    //TODO Si el usuario NO ti ene cuenta, el register deberá llevarle a la pantalla de creacion de personaje y automaticamente aplicar un sprite base, en caso contrario más tarde tendremos problemas de nullexception a la hora de hacer login
    //var pilot: Worker = Worker("Test", 1, 1, 1, 100, null, 2, 100, 100, Worker.Job.PILOT)
    //var usr: User = User(0, false, "Falso", "Falso", "Un usuario", Date(), pilot)

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

            R.id.settSpa -> setLocale("es")
            R.id.settEng -> setLocale("Default value")
            R.id.settMute -> Toast.makeText(this,"Sound muted", Toast.LENGTH_SHORT).show()
            R.id.settUnmute -> Toast.makeText(this,"Sound unmuted", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun toCharacter(view: View) {

        var name = findViewById<EditText>(R.id.etUser)
        var sName = name.text.toString()

        var pass = findViewById<EditText>(R.id.etPass)
        var sPass = pass.text.toString()

        if (sName.equals("user") && sPass.equals("user")) {
            val i = Intent(this, Characters::class.java)
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
