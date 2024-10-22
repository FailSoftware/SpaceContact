package com.example.spacecontact

import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.spacecontact.entity.Ship
import com.example.spacecontact.entity.ShipPart
import com.example.spacecontact.entity.Worker
import com.example.spacecontact.gameFunctions.*
import kotlinx.android.synthetic.main.activity_game.*
import pl.droidsonroids.gif.GifImageView
import java.lang.ClassCastException
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.collections.ArrayList

class Game : PrefMenu() {
    private lateinit var framChar: FrameLayout;
    private lateinit var ship: Ship;
    private lateinit var enemyShip: Ship;
    private var selectedWorker: Int = 0;
    private var enemyTotalTurns: Int = 0;
    private lateinit var selectedShipPart: ShipPart;
    private var workerAction: String = "";
    private lateinit var msgBox: TextView;
    private lateinit var injuredWorker: Worker;
    private lateinit var turnBtn : Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )

        val lg = LoadGame()
        lg.run()

        turnBtn = findViewById(R.id.btnEndTurn)
        msgBox = findViewById(R.id.msgBox)
        framChar = findViewById(R.id.framChar)
        ship = lg.ship

        updateAdapters()
        updateShipInfo()
    }



    fun loadCharData(view: View) {
        //Toast.makeText(this, "pos >> " + view.getTag().toString(), Toast.LENGTH_LONG).show();
        try {
            var thisWorker: Worker;
            var tvWorkerName: TextView = findViewById(R.id.tvWorkerName)
            var tvWorkerJob: TextView = findViewById(R.id.tvWorkerJob)
            var tvWorkerTurns: TextView = findViewById(R.id.tvWorkerTurns)
            var pbHp: ProgressBar = findViewById(R.id.pbHp)
            var pbFat: ProgressBar = findViewById(R.id.pbFat)
            var pbHung: ProgressBar = findViewById(R.id.pbHung)
            var tvWorkerLore: TextView = findViewById(R.id.tvWorkerLore)
            var otherFrame : FrameLayout = findViewById(R.id.frameOthers)
            hideWindows()


            if (framChar.visibility == View.VISIBLE && tvWorkerName.text == ship.crew[view.getTag() as Int].name) {
                hideWindows()
                turnBtn.visibility = View.VISIBLE


            } else {
                thisWorker = ship.crew[view.getTag() as Int]
                if (thisWorker.currentTurns > 0) {
                    selectedWorker = view.getTag() as Int;
                    framChar.visibility = View.VISIBLE
                    tvWorkerName.text = thisWorker.name
                    tvWorkerJob.text = thisWorker.job.toString()
                    tvWorkerTurns.text =
                        "[" + ship.crew[view.getTag() as Int].currentTurns.toString() + " / " + ship.crew[view.getTag() as Int].totalTurns.toString() + "]"
                    pbHp.progress = thisWorker.currentHealth
                    pbFat.progress = thisWorker.fatigue
                    pbHung.progress = thisWorker.hungerLevel
                    tvWorkerLore.text = thisWorker.description
                } else {
                    msgBox.text = thisWorker.name + " doesn't have any turns left"
                }

            }
        } catch (e: IllegalStateException) {
            hideWindows()
            turnBtn.visibility = View.VISIBLE
            msgBox.text =
                "This is a reserved space for a new worker you can get as a reward after defeating foes"
        }
    }



    fun battleEnemy(view: View) {
        msgBox.setText("Entering combat mode")
        var battleBtn: GifImageView = findViewById(R.id.gifImageView2)
        battleBtn.visibility = View.GONE
        enemyShip = Ship(ship.difficulty)
        hideWindows()
        turnBtn.visibility = View.VISIBLE
    }



    private fun enemyTurn(view: View){
        if (enemyTotalTurns > 0){
            msgBox.text = enemyShip.EnemyAction(ship)
            enemyTotalTurns--

            updateShipInfo()
            updateAdapters()
            checkBattleEvent()
        } else {
            msgBox.text = "Enemy is out of turns"
            gridLay.visibility = View.VISIBLE
            turnBtn.setText("End turn")
            turnBtn.setOnClickListener{
                endTurn(view)
            }
        }
    }

    fun endTurn(view: View){
        ship.RestoreTurns()
        enemyShip.RestoreTurns()
        enemyTotalTurns = enemyShip.totalTurns

        gridLay.visibility = View.GONE
        var btn : Button = findViewById(R.id.btnEndTurn)
        btn.setText("Next")
        btn.setOnClickListener{
            enemyTurn(view)
        }

        enemyTurn(view)
    }

    fun workerActTurn(view: View) {
        var msgText: String = "";
        var currentEnemyShip: Ship? = null;
        var currentSelectedShipPart: ShipPart? = null;
        var currentInjuredWorker: Worker? = null;
        var customAction : String = "";


        try{
            var customTag : CustomTag = view.tag as CustomTag
            customAction = customTag.action
            var position = customTag.position

            if (customAction == "Heal"){
                currentInjuredWorker = ship.crew[position.toInt()]
            } else if(customAction == "Repair"){
                selectedShipPart = ship.part[position.toInt()]
            }

        } catch (e : Exception) {

        }



        if (::enemyShip.isInitialized) {
            currentEnemyShip = enemyShip
        }
        if (::selectedShipPart.isInitialized) {
            currentSelectedShipPart = selectedShipPart
        }
        if (::injuredWorker.isInitialized) {
            currentInjuredWorker = injuredWorker
        }

        try {
            workerAction = view.getTag() as String
        } catch (e : ClassCastException){
            workerAction = customAction;
        }


        msgText = ship.playerTurn(
            workerAction,
            ship.crew[selectedWorker],
            currentEnemyShip,
            currentSelectedShipPart,
            currentInjuredWorker
        )

        msgBox.text = msgText


        hideWindows()
        updateShipInfo()
        updateAdapters()
        checkBattleEvent()
        turnBtn.visibility = View.VISIBLE
    }

    fun checkBattleEvent() {
        var currentEnemyShip: Ship? = null;

        //Player wins
        if (::enemyShip.isInitialized) {
            currentEnemyShip = enemyShip
            if (currentEnemyShip.currentHealth <= 0) {
                msgBox.text = "You won the battle"
                ship.battleRewards(this, enemyShip);
            }
        }

        //Player looses
        if (ship.currentHealth <= 0) {
            msgBox.text = "Your ship got destroyed"
        } else if (ship.crew[0].currentHealth <= 0) {
            msgBox.text = "You died in action"
        }
    }

    //Updates the information displayed at the top of the screen
    fun updateShipInfo() {
        var s1: TextView = findViewById(R.id.tvS1)
        var s2: TextView = findViewById(R.id.tvS2)
        var s3: TextView = findViewById(R.id.tvS3)
        var s4: TextView = findViewById(R.id.tvS4)
        var s5: TextView = findViewById(R.id.tvS5)

        var w1: TextView = findViewById(R.id.tvS6)
        var w2: TextView = findViewById(R.id.tvS7)
        var w3: TextView = findViewById(R.id.tvS8)


        s1.setText("Health [" + ship.currentHealth + " | " + ship.totalHealth + "] ")
        s2.setText("Credits [" + ship.credit + "] ")
        s3.setText("Oxygen [" + ship.currentOxygen + " | " + ship.totalOxygen + "] ")
        s4.setText("Food [" + ship.currentFood + " | " + ship.totalFood + "] ")
        s5.setText("Fuel [" + ship.currentFuel + " | " + ship.totalFuel + "] ")

        w1.setText("Firepower = " + ship.weapon.weaponPower)
        w2.setText("Crit chance = " + ship.weapon.weaponCritChance)
        w3.setText("Crit mult = " + ship.weapon.weaponCritMultiplier)

    }

    //Calls every adapter to update layouts
    fun updateAdapters() {
        var gridLay: GridView = findViewById(R.id.gridLay)
        var adapterWorkers: AdapterWorkers =
            AdapterWorkers(
                this,
                (ship.crew).toCollection(ArrayList())
            )
        gridLay.adapter = adapterWorkers;

        var listRepair: ListView = findViewById(R.id.repairList)
        var adapterRepair: AdapterRepair =
            AdapterRepair(
                this, (ship.part).toCollection(ArrayList())
            )
        listRepair.adapter = adapterRepair

        var listHeal : ListView = findViewById(R.id.healList)
        var adapaterHeal: AdapterHeal = AdapterHeal(this, (ship.crew).toCollection(ArrayList()))
        listHeal.adapter = adapaterHeal
    }

    fun optionsChar(view: View) {
        frameOthers.visibility = View.VISIBLE
    }

    fun exitOptionMenu(view: View) {
        hideWindows()
    }

    fun heal(view: View) {
        hideWindows()
        frameRHeal.visibility = View.VISIBLE
    }

    fun repair(view: View){
        hideWindows()
        var listRepair: FrameLayout = findViewById(R.id.frameRepair)
        listRepair.visibility = View.VISIBLE
    }

    fun hideWindows(){
        var listRepair: FrameLayout = findViewById(R.id.frameRepair)
        listRepair.visibility = View.GONE
        frameRHeal.visibility = View.GONE
        framChar.visibility = View.GONE
        frameOthers.visibility = View.GONE
        turnBtn.visibility = View.GONE
    }

}