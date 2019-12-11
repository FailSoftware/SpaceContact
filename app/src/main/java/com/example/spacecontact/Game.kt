package com.example.spacecontact

import android.os.Bundle
import android.widget.GridView
import com.example.spacecontact.entity.Ship
import com.google.gson.Gson
import kotlin.collections.ArrayList

class Game : Login() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var gridLay : GridView = findViewById(R.id.gridLay)
        var bundle = intent.extras as Bundle

        //PASAR EL SHIP DEL JUGADOR EN UN OBJETO JSON SIEMPRE
        val json : String ?= bundle.getString("json")
        var gson : Gson = Gson()

        val ship : Ship = gson.fromJson(json, Ship::class.java)

        var adapterWorkers: AdapterWorkers =
            AdapterWorkers(this, (ship.crew).toCollection(ArrayList()))
        gridLay.adapter = adapterWorkers;
    }

}