package com.example.spacecontact

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.spacecontact.gameFunctions.MyService
import com.google.firebase.auth.FirebaseAuth
import java.util.*

open class PrefMenu : AppCompatActivity() {
    var c: Context = this
    val preferencesfieldName = "Preferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref_menu)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        if (settingsfile.getBoolean(
                "language",
                true
            ) && (!settingsfile.contains("iniciado") || settingsfile.getBoolean("iniciado", true))
        ) {
            var myeditor: SharedPreferences.Editor = settingsfile.edit()
            myeditor.putBoolean("iniciado", false)
            myeditor.commit()
            setLocale("en")
        }
        if (settingsfile.getBoolean(
                "language",
                false
            ) && (!settingsfile.contains("iniciado") || settingsfile.getBoolean("iniciado", true))
        ) {
            var myeditor: SharedPreferences.Editor = settingsfile.edit()
            myeditor.putBoolean("iniciado", false)
            myeditor.commit()
            setLocale("es")

        }
        if (settingsfile.getBoolean("muisc", true) == true) {
            setMusicOn()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_settings, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.settSpa -> setLocale("es")
            R.id.settEng -> setLocale("en")
            R.id.settMute -> {
                setMusicOff()
            }
            R.id.settUnmute -> {
                setMusicOn()
            }

            R.id.menuContact -> {
                val i = Intent(this, Contact::class.java)
                startActivity(i)
            }

            R.id.logOff -> {
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
            }

            R.id.allExit -> {
                alertExit()
            }
        }


        return super.onOptionsItemSelected(item)
    }

    fun setMusicOn() {
        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        var myeditor: SharedPreferences.Editor = settingsfile.edit();
        myeditor.putBoolean("muisc", true)
        myeditor.apply();
        startService(Intent(baseContext, MyService::class.java))
    }

    fun setMusicOff() {
        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        var myeditor: SharedPreferences.Editor = settingsfile.edit();
        myeditor.putBoolean("muisc", false)
        myeditor.apply();
        stopService(Intent(baseContext, MyService::class.java))
    }

    fun setLocale(lang: String) {
        val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
        var myeditor: SharedPreferences.Editor = settingsfile.edit();
        if (lang.equals("es")) {
            myeditor.putBoolean("language", false)
        } else {
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

        var title: String = this.getString(R.string.alertTitle)
        var msg: String = this.getString(R.string.alertMsg)
        val bdr = AlertDialog.Builder(this)
        bdr.setTitle(title)
        bdr.setMessage(msg)
        bdr.setPositiveButton(R.string.alertYes, DialogInterface.OnClickListener { dialog, id ->
            moveTaskToBack(true)
            val settingsfile = getSharedPreferences(preferencesfieldName, Context.MODE_PRIVATE)
            var myeditor: SharedPreferences.Editor = settingsfile.edit()
            myeditor.putBoolean("iniciado", true)
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
