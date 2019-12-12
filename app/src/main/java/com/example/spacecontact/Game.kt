package com.example.spacecontact

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import com.example.spacecontact.entity.Ship
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.collections.ArrayList

class Game : Login() {
    lateinit var gridLay : GridView;
    lateinit var framChar : FrameLayout;

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

        framChar = findViewById(R.id.framChar)
    }

    fun loadCharData(view: View){

        if(framChar.visibility == View.VISIBLE){
            framChar.visibility = View.GONE
        }else{
            framChar.visibility = View.VISIBLE
        }


    }

    fun optionsChar(view: View) {
        frameOthers.visibility = View.VISIBLE
        framChar.visibility = View.GONE
    }

    fun exitOptionMenu(view: View){
        frameOthers.visibility = View.GONE
    }

}