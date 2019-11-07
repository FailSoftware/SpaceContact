package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO que alguien ponga que salga el logo de la empresa aqui 3 segundos, porfaplz :3
        loadLogin()
    }

    fun loadLogin(){
     var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }

}
