package com.example.spacecontact

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.gameFunctions.AdapterRepair
import com.example.spacecontact.gameFunctions.AdapterWorkers
import com.example.spacecontact.gameFunctions.LoadGame
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.IllegalStateException
import kotlin.collections.ArrayList

class Game : PrefMenu() {
    lateinit var gridLay: GridView;
    lateinit var framChar: FrameLayout;
    lateinit var ship: Ship;
    var selectedWorker: Int = 0;
    lateinit var msgBox : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

        msgBox = findViewById(R.id.msgBox)

        val lg = LoadGame()
        lg.run()

        var gridLay: GridView = findViewById(R.id.gridLay)
        ship = lg.ship

        var listRepair: ListView = findViewById(R.id.repairList)

        var adapterRepair : AdapterRepair =
            AdapterRepair(this,(ship.part).toCollection(ArrayList())
        )

        listRepair.adapter = adapterRepair

        var adapterWorkers: AdapterWorkers =
            AdapterWorkers(
                this,
                (ship.crew).toCollection(ArrayList())
            )
        gridLay.adapter = adapterWorkers;

        framChar = findViewById(R.id.framChar)
    }

    fun loadCharData(view: View) {
        //Toast.makeText(this, "pos >> " + view.getTag().toString(), Toast.LENGTH_LONG).show();

        try {

            //TODO Add progress bars
            var tvWorkerName: TextView = findViewById(R.id.tvWorkerName)
            var tvWorkerJob: TextView = findViewById(R.id.tvWorkerJob)
            var tvWorkerTurns: TextView = findViewById(R.id.tvWorkerTurns)


            if (framChar.visibility == View.VISIBLE && tvWorkerName.text == ship.crew[view.getTag() as Int].name) {
                framChar.visibility = View.GONE
            } else {
                selectedWorker = view.getTag() as Int;
                framChar.visibility = View.VISIBLE
                tvWorkerName.text = ship.crew[view.getTag() as Int].name
                tvWorkerJob.text = ship.crew[view.getTag() as Int].job.toString()
                tvWorkerTurns.text =
                    "[" + ship.crew[view.getTag() as Int].currentTurns.toString() + " / " + ship.crew[view.getTag() as Int].totalTurns.toString() + "]"

            }
        } catch (e: IllegalStateException) {
            framChar.visibility = View.GONE
        }
    }

    fun attackFun(view: View){
        val msgText : String = ship.playerTurn("Attack", ship.crew[selectedWorker], null, null, null)
        msgBox.text = msgText
        framChar.visibility = View.GONE

    }

    fun optionsChar(view: View) {
        frameOthers.visibility = View.VISIBLE
        framChar.visibility = View.GONE
    }

    fun exitOptionMenu(view: View) {
        frameOthers.visibility = View.GONE
    }

}