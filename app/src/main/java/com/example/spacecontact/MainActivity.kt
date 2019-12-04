package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacecontact.entity.Ship

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO que alguien ponga que salga el logo de la empresa aqui 3 segundos, porfaplz :3
        loadLogin()

        //TODO Este apartado solo está por motivos de prueba, borrar al tener el menu principal acabado
        //loadCharacter()
        //loadNewGame()

    }

    fun loadLogin(){
     var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)

    }

}
