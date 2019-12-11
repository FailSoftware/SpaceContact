package com.example.spacecontact

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.spacecontact.entity.User
import com.example.spacecontact.entity.Worker
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import java.time.LocalDate
import java.util.*


open class Login : AppCompatActivity() {


    //TODO Si el usuario ya tiene cuenta, hacer que los datos de piloto se carguen directamente desde la base de datos.
    //TODO Si el usuario NO ti ene cuenta, el register deberá llevarle a la pantalla de creacion de personaje y automaticamente aplicar un sprite base, en caso contrario más tarde tendremos problemas de nullexception a la hora de hacer login
    //var pilot: Worker = Worker("Test", 1, 1, 1, 100, null, 2, 100, 100, Worker.Job.PILOT)
    //var usr: User = User(0, false, "Falso", "Falso", "Un usuario", Date(), pilot)


    lateinit var userMail : EditText
    lateinit var userPass : EditText
    lateinit var fbAut : FirebaseAuth

    var c : Context = this
    val preferencesfieldName  = "Preferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        if( settingsfile.getBoolean("language",true)&& (!settingsfile.contains("iniciado")||settingsfile.getBoolean("iniciado",true))){
            var myeditor: SharedPreferences.Editor  = settingsfile.edit();
            myeditor.putBoolean("iniciado",false);
            myeditor.commit()
            setLocale("en")
        }
        if( settingsfile.getBoolean("language",false)&& (!settingsfile.contains("iniciado")||settingsfile.getBoolean("iniciado",true))){
            var myeditor: SharedPreferences.Editor  = settingsfile.edit();
            myeditor.putBoolean("iniciado",false);
            myeditor.commit()
            setLocale("es")

        }


        userMail = findViewById(R.id.etUser)
        userPass = findViewById(R.id.etPass)
        fbAut = FirebaseAuth.getInstance()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflate : MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_settings, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.settSpa -> setLocale("es")
            R.id.settEng -> setLocale("en")
            R.id.settMute -> {stopService(Intent(baseContext,MyService::class.java)) }
            R.id.settUnmute -> {startService(Intent(baseContext,MyService::class.java))}


            R.id.menuContact ->{
                val i = Intent(this, Contact::class.java)
                startActivity(i)
            }

            R.id.menuBack ->{
                val i = Intent(this, MainMenu::class.java)
                startActivity(i)
            }
            R.id.logOff ->{
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
            }

            R.id.allExit ->{
                alertExit()
            }
        }


        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NewApi")
    fun toMenu(view: View) {
        val wor : Worker = Worker(view.context, 1)
        val usr : User = User(0, false, "NombreComp", "NombreUsuario", "Descripcion", LocalDate.now(), wor)

        var msg : String = this.getString(R.string.welcomeMsg)
        fbAut.signInWithEmailAndPassword(userMail.text.toString(), userPass.text.toString()).addOnCompleteListener {
            if (it.isSuccessful){
                val i = Intent(this, MainMenu::class.java)
                i.putExtra("usr", usr)
                i.putExtra("wor", wor)
                startActivity(i)
                Toast.makeText(this, msg+": "+ userMail.text.toString(), Toast.LENGTH_SHORT).show()
            }else{

            }
        }
    }

    fun toRegister(view: View) {

        val i = Intent(this, Register::class.java)
        startActivity(i)


    }

    fun setLocale(lang: String) {
        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        var myeditor: SharedPreferences.Editor  = settingsfile.edit();
        if(lang.equals("es")){
            myeditor.putBoolean("language", false)
        }
        else{
            myeditor.putBoolean("language", true);
        }
        myeditor.apply();
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, c::class.java)
        finish()
        startActivity(refresh)
    }


    fun alertExit() {

        var title : String = this.getString(R.string.alertTitle)
        var msg : String = this.getString(R.string.alertMsg)
        val bdr = AlertDialog.Builder(this)
        bdr.setTitle(title)
        bdr.setMessage(msg)
        bdr.setPositiveButton(R.string.alertYes, DialogInterface.OnClickListener { dialog, id ->
            moveTaskToBack(true)
            val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
            var myeditor: SharedPreferences.Editor  = settingsfile.edit()
            myeditor.putBoolean("iniciado",true)
            myeditor.commit()
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        })
        bdr.setNegativeButton(R.string.alertNo, DialogInterface.OnClickListener { dialog, id ->
            // User cancelled the dialog and do nothing.
        })
        bdr.create()
        bdr.show()

    }

    override fun onBackPressed() {
        alertExit()
    }

}
