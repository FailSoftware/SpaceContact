package com.example.spacecontact

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacecontact.R
import com.example.spacecontact.entity.Npc
import kotlinx.android.synthetic.main.activity_planet.*

class Planet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet)
        var p=ArrayList<String>()
        p.add("prueba")
        var pruebas=  Npc("Pruebas",1,1,1,1,1,p,BitmapFactory.decodeResource(this.resources,
            R.drawable.suitzero
        ))
        textView3.text=pruebas.name
        lore.text=pruebas.lore.get(0)
        imageView2.setImageBitmap(pruebas.npc)

    }

}
