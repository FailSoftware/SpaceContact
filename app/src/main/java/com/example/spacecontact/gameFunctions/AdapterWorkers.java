package com.example.spacecontact.gameFunctions;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacecontact.R;
import com.example.spacecontact.entity.Worker;

import java.util.ArrayList;

public class AdapterWorkers extends BaseAdapter {

    private Context context;
    private ArrayList<Worker> workers;

    private String hunger;
    private String fatige;
    private String turns;
    private String jobA;

    public AdapterWorkers(Context context, ArrayList<Worker> workers) {
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
        LayoutInflater lInflater = ((Activity) context).getLayoutInflater();
        convertView = lInflater.inflate(R.layout.adapter_workers, null);
        convertView.setTag(position);

        TextView nAdapt = convertView.findViewById(R.id.tvName);
        TextView fAdapt = convertView.findViewById(R.id.tvFat);
        TextView hAdapt = convertView.findViewById(R.id.tvHunger);
        TextView tAdapt = convertView.findViewById(R.id.tvTurns);
        TextView jAdapt = convertView.findViewById(R.id.tvJobAdapt);
        ImageView iAdapt = convertView.findViewById(R.id.ivWorker);

        Worker contextWorkers;

        hunger = context.getString(R.string.hungerChar);
        fatige = context.getString(R.string.fatigeChar);
        turns = context.getString(R.string.turnsChar);
        jobA = context.getString(R.string.jobChar);
        try {
            contextWorkers = workers.get(position);

            nAdapt.setText(contextWorkers.getName());
            fAdapt.setText(fatige + ": [" + contextWorkers.getFatigue() + "/100]");
            hAdapt.setText(hunger + ": [" + contextWorkers.getHungerLevel() + "/100]");
            tAdapt.setText(turns + ": [" + contextWorkers.getCurrentTurns() + "/" + contextWorkers.getTotalTurns()+"] ");
            jAdapt.setText(jobA +": " + contextWorkers.getJob().toString());
            iAdapt.setImageBitmap(contextWorkers.getSprite());


        } catch (NullPointerException e) {
            nAdapt.setText("Empty space");
            fAdapt.setText("");
            hAdapt.setText("");
            tAdapt.setText("");
            jAdapt.setText("");
            //iAdapt.setImageBitmap(contextWorkers.getSprite());
        } catch (IndexOutOfBoundsException e) {

        }


        return convertView;
    }


}
