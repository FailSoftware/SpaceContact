package com.example.spacecontact.gameFunctions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.spacecontact.R
import com.example.spacecontact.entity.Worker
import java.lang.IllegalStateException

class AdapterHeal constructor(context: Context, workerHealing : ArrayList<Worker>):BaseAdapter() {
    private var context : Context = context
    private var workerHealing : ArrayList<Worker> = workerHealing
    private lateinit var hpW : String
    private lateinit var wName: String
    private lateinit var wPr: String


    override fun getCount(): Int {
        return workerHealing.size
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var v : View? = convertView
        var lInflater: LayoutInflater = (context as Activity).layoutInflater
        v = lInflater.inflate(R.layout.adapter_healin, null)
        v.setTag(1, position)

        var wNameAda : TextView = v.findViewById(R.id.tvWname)
        var wHpAda : TextView = v.findViewById(R.id.tvWhp)
        var wProbAda : TextView = v.findViewById(R.id.tvWp)


        hpW = context.getString(R.string.shipHp)
        wName = context.getString(R.string.wNameAd)

        wPr =""

        try{
            var contextHealing: Worker
            contextHealing = workerHealing.get(position)

            wNameAda.setText(context.getString(R.string.wNameAd)+": "+contextHealing.name)
            wHpAda.setText(contextHealing.currentHealth.toString() +"/"+contextHealing.totalHealth)
            if(contextHealing.onFire){
                wPr += " ["+context.getString(R.string.fire)+"] "

            }

            if(contextHealing.onShock){
                wPr += " ["+context.getString(R.string.shock)+"] "
            }

            if(contextHealing.wounded){
                wPr += " ["+context.getString(R.string.wounded)+"] "

            }
            wProbAda.setText(context.getString(R.string.shipProblem)+": "+wPr)
        } catch (e : IllegalStateException){
            wNameAda.setText("Empty space")
            wHpAda.setText("")
            wProbAda.setText("")
        }


        return v


    }
}