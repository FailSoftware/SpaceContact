package com.example.spacecontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import com.example.spacecontact.entity.Ship
import kotlin.collections.ArrayList

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var gridLay : GridView = findViewById(R.id.gridLay)
        val ship : Ship = Ship(this)

        var adapter: Adapter = Adapter(this, (ship.crew).toCollection(ArrayList()))
        gridLay.adapter = adapter;
    }

}