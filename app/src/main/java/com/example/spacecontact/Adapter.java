package com.example.spacecontact;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacecontact.entity.Worker;

import java.util.ArrayList;
import java.util.Iterator;

public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Worker> workers;

    public Adapter(Context context, ArrayList<Worker> workers){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = ((Activity)context).getLayoutInflater();
        convertView  = lInflater.inflate(R.layout.adapter, null);

        TextView nAdapt = convertView.findViewById(R.id.tvName);
        TextView fAdapt = convertView.findViewById(R.id.tvFat);
        TextView hAdapt = convertView.findViewById(R.id.tvHunger);
        TextView tAdapt = convertView.findViewById(R.id.tvTurns);
        ImageView iAdapt = convertView.findViewById(R.id.ivWorker);

        Iterator iter = workers.iterator();
        Worker contextWorkers;
        Worker auxWorker;
        int contador = 0;

        do{
            auxWorker = (Worker) iter.next();
            contador ++;
        }while(iter.hasNext() && contador <= position);
        contextWorkers = auxWorker;


        nAdapt.setText(contextWorkers.getName());
        fAdapt.setText(contextWorkers.getFatigue() + "");
        hAdapt.setText(contextWorkers.getHungerLevel() + "");
        tAdapt.setText(contextWorkers.getCurrentTurns() + "");
        iAdapt.setImageBitmap(contextWorkers.getSprite());


        return convertView;
    }



}
