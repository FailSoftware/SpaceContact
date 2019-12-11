package com.example.spacecontact

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import com.example.spacecontact.entity.Ship
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import kotlin.collections.ArrayList

class Game : Login() {
    private lateinit var playerShip : Ship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var gridLay : GridView = findViewById(R.id.gridLay)
        //PASAR EL SHIP DEL JUGADOR EN UN OBJETO JSON SIEMPRE

        val gson = Gson()
        val f = File("./save.json")
        val path : File = this.filesDir
        Log.d("json",path.toString())
        val jr = JsonReader(FileReader(path.toString()+ "/save.json"))
        val temp = gson.fromJson<Ship>(jr, Ship::class.java)
        playerShip = temp


        var adapterWorkers: AdapterWorkers =
            AdapterWorkers(this, (playerShip.crew).toCollection(ArrayList()))
        gridLay.adapter = adapterWorkers;
    }

}