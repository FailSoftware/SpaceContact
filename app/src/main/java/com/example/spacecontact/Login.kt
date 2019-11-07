package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun toCharacter(view: View){

        var name = findViewById(R.id.etUser) as EditText
        var sName = name.getText().toString()

        var pass = findViewById(R.id.etPass) as EditText
        var sPass = pass.getText().toString()

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
