package com.example.spacecontact

import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.ShipPart
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.AdapterRepair
import com.example.spacecontact.gameFunctions.AdapterWorkers
import com.example.spacecontact.gameFunctions.LoadGame
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.IllegalStateException
import kotlin.collections.ArrayList

class Game : PrefMenu() {
    private lateinit var framChar: FrameLayout;
    private lateinit var ship: Ship;
    private lateinit var enemyShip: Ship;
    private var selectedWorker: Int = 0;
    private lateinit var selectedShipPart : ShipPart;
    private var workerAction : String = "";
    private lateinit var msgBox : TextView;
    private lateinit var injuredWorker: Worker;


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
            var thisWorker : Worker;

            var tvWorkerName: TextView = findViewById(R.id.tvWorkerName)
            var tvWorkerJob: TextView = findViewById(R.id.tvWorkerJob)
            var tvWorkerTurns: TextView = findViewById(R.id.tvWorkerTurns)
            var pbHp : ProgressBar = findViewById(R.id.pbHp)
            var pbFat : ProgressBar = findViewById(R.id.pbFat)
            var pbHung : ProgressBar = findViewById(R.id.pbHung)


            if (framChar.visibility == View.VISIBLE && tvWorkerName.text == ship.crew[view.getTag() as Int].name) {
                framChar.visibility = View.GONE
            } else {
                thisWorker = ship.crew[view.getTag() as Int]
                selectedWorker = view.getTag() as Int;
                framChar.visibility = View.VISIBLE
                tvWorkerName.text = thisWorker.name
                tvWorkerJob.text = thisWorker.job.toString()
                tvWorkerTurns.text =
                    "[" + ship.crew[view.getTag() as Int].currentTurns.toString() + " / " + ship.crew[view.getTag() as Int].totalTurns.toString() + "]"
                pbHp.progress = thisWorker.currentHealth
                pbFat.progress = thisWorker.fatigue
                pbHung.progress = thisWorker.hungerLevel
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

    fun workerActTurn(view: View){
        var msgText : String = "";
        var currentEnemyShip : Ship? = null;
        var currentSelectedShipPart : ShipPart? = null;
        var currentInjuredWorker : Worker? = null;

        if (::enemyShip.isInitialized){
            currentEnemyShip = enemyShip
        }
        if (::selectedShipPart.isInitialized){
            currentSelectedShipPart = selectedShipPart
        }
        if (::injuredWorker.isInitialized){
            currentInjuredWorker = injuredWorker
        }

        workerAction = view.getTag() as String

        msgText = ship.playerTurn(workerAction, ship.crew[selectedWorker], currentEnemyShip , currentSelectedShipPart, currentInjuredWorker)

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