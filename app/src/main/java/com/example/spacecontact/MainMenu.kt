package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainMenu : AppCompatActivity() {

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
}
