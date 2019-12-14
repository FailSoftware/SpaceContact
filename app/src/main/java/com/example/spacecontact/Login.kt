package com.example.spacecontact

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.MyService
import com.example.spacecontact.gameFunctions.SaveGame
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.util.*


open class Login : PrefMenu() {
    val EXTERNAL_STORAGE_WRITE = 0
    val EXTERNAL_STORAGE_READ = 0

    //TODO Si el usuario ya tiene cuenta, hacer que los datos de piloto se carguen directamente desde la base de datos.
    //TODO Si el usuario NO ti ene cuenta, el register deberá llevarle a la pantalla de creacion de personaje y automaticamente aplicar un sprite base, en caso contrario más tarde tendremos problemas de nullexception a la hora de hacer login
    //var pilot: Worker = Worker("Test", 1, 1, 1, 100, null, 2, 100, 100, Worker.Job.PILOT)
    //var usr: User = User(0, false, "Falso", "Falso", "Un usuario", Date(), pilot)


    lateinit var userMail: EditText
    lateinit var userPass: EditText
    lateinit var fbAut: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

        userMail = findViewById(R.id.etUser)
        userPass = findViewById(R.id.etPass)
        fbAut = FirebaseAuth.getInstance()


        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                EXTERNAL_STORAGE_WRITE
            )

        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                EXTERNAL_STORAGE_READ
            )
        }

    }

    @SuppressLint("NewApi")
    fun toMenu(view: View) {
        var msg: String = this.getString(R.string.welcomeMsg)
        fbAut.signInWithEmailAndPassword(userMail.text.toString(), userPass.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val i = Intent(this, MainMenu::class.java)
                    startActivity(i)
                    Toast.makeText(this, msg + ": " + userMail.text.toString(), Toast.LENGTH_SHORT)
                        .show()
                } else {

                }
            }
    }

    fun toRegister(view: View) {

        val i = Intent(this, Register::class.java)
        startActivity(i)

    }
}
