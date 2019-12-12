package com.example.spacecontact.gameFunctions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.spacecontact.R;
import com.example.spacecontact.entity.Worker;
import com.google.firebase.database.snapshot.Index;

import java.util.ArrayList;
import java.util.Iterator;

public class AdapterWorkers extends BaseAdapter {

    private Boolean frameActivated = true;
    private Context context;
    private ArrayList<Worker> workers;

    private String hunger;
    private String fatige;
    private String turns;
    public AdapterWorkers(Context context, ArrayList<Worker> workers){
        this.context = context;
        this.workers = workers;
    }


    @Override
    public int getCount() {
        return workers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater lInflater = ((Activity)context).getLayoutInflater();
        convertView  = lInflater.inflate(R.layout.adapter_workers, null);

        TextView nAdapt = convertView.findViewById(R.id.tvName);
        TextView fAdapt = convertView.findViewById(R.id.tvFat);
        TextView hAdapt = convertView.findViewById(R.id.tvHunger);
        TextView tAdapt = convertView.findViewById(R.id.tvTurns);
        ImageView iAdapt = convertView.findViewById(R.id.ivWorker);



        Iterator iter = workers.iterator();
        Worker contextWorkers;
        int contador = 0;

        do{
            contador ++;
        }while(iter.hasNext() && contador <= position);




        hunger = context.getString(R.string.hungerChar);
        fatige = context.getString(R.string.fatigeChar);
        turns = context.getString(R.string.turnsChar);

        try {
            contextWorkers = workers.get(contador);

            nAdapt.setText(contextWorkers.getName());
            fAdapt.setText(fatige+": " +contextWorkers.getFatigue() + "/100");
            hAdapt.setText(hunger+": "+contextWorkers.getHungerLevel() + "/100");
            tAdapt.setText(turns+": "+contextWorkers.getCurrentTurns() + "/" + contextWorkers.getTotalTurns());
            iAdapt.setImageBitmap(contextWorkers.getSprite());

            Log.d("Adapter", contador + " >> " + contextWorkers.getName() + " == " + workers.get(contador).getName());
            for (Worker w: workers) {
                Log.d("Adapter", "Workers >> " +  w.getName());
            }


        } catch (NullPointerException e){
            nAdapt.setText("Empty space");
            fAdapt.setText("");
            hAdapt.setText("");
            tAdapt.setText("");
           //iAdapt.setImageBitmap(contextWorkers.getSprite());
        } catch (IndexOutOfBoundsException e){

        }



        return convertView;
    }



}
