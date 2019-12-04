package com.example.spacecontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.GridView
import android.widget.Toast
import com.example.spacecontact.entity.Ship
import java.util.*
import kotlin.collections.ArrayList

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var gridLay : GridView = findViewById(R.id.gridLay)
        val ship : Ship = Ship(this)

        var adapterWorkers: AdapterWorkers =
            AdapterWorkers(this, (ship.crew).toCollection(ArrayList()))
        gridLay.adapter = adapterWorkers;
    }

}