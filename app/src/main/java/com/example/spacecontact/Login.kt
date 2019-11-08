package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import java.util.*

class Login : AppCompatActivity() {


    //TODO Si el usuario ya tiene cuenta, hacer que los datos de piloto se carguen directamente desde la base de datos.
    //TODO Si el usuario NO ti ene cuenta, el register deberá llevarle a la pantalla de creacion de personaje y automaticamente aplicar un sprite base, en caso contrario más tarde tendremos problemas de nullexception a la hora de hacer login
    var pilot: Worker = Worker("Test", 1, 1, 1, 100, null, 2, 100, 100, Worker.Job.PILOT)
    //var usr: User = User(0, false, "Falso", "Falso", "Un usuario", Date(), pilot)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
}
