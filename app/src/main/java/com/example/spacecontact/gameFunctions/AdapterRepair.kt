package com.example.spacecontact.gameFunctions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.spacecontact.R
import com.example.spacecontact.entity.ShipPart



class AdapterRepair constructor(context : Context, repairParts : ArrayList<ShipPart>): BaseAdapter() {

    private var context : Context = context
    private var repairParts : ArrayList<ShipPart> = repairParts
    private lateinit var parts : String
    private lateinit var problem : String
    private lateinit var hp : String


    override fun getCount(): Int {
        return repairParts.size
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        var lInflater = (context as Activity).layoutInflater
        v = lInflater.inflate(R.layout.adapter_repair, null)
        v.setTag(position)



        var hpAdap : TextView = v.findViewById(R.id.tvShipHp)
        var partsAdap : TextView = v.findViewById(R.id.tvShipPart)
        var problemAdap : TextView = v.findViewById(R.id.tvProblem)

        hp = context.getString(R.string.shipHp)
        parts = context.getString(R.string.shipPart)
        problem =""



        var contextShipPart: ShipPart

        contextShipPart = repairParts.get(position)
        hpAdap.setText(context.getString(R.string.shipHp)+": "+contextShipPart.currentHealth.toString()+"/"+ contextShipPart.totalHealth.toString())
        partsAdap.setText(context.getString(R.string.shipPart)+": "+contextShipPart.partName)
        if(contextShipPart.onFire){
            problem += " ["+context.getString(R.string.fire)+"] "

        }

        if(contextShipPart.onShock){
            problem += " ["+context.getString(R.string.shock)+"] "
        }

        if(contextShipPart.pierced){
            problem += " ["+context.getString(R.string.pierce)+"] "

        }
        problemAdap.setText(context.getString(R.string.shipProblem)+": "+problem)

    return v
    }






}