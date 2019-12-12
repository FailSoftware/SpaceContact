package com.example.spacecontact.gameFunctions;

import android.os.Environment;
import android.util.Log;

import com.example.spacecontact.Preferences;
import com.example.spacecontact.entity.Ship;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame implements Runnable{
    private volatile String json;
    private Ship ship;

    public SaveGame(Ship ship){
        this.ship = ship;
    }

    @Override
    public void run(){
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.setPrettyPrinting();
            Gson gson = gb.create();
            String json = gson.toJson(ship);
            FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "sps.json"));
            Log.d("save", Environment.getExternalStorageDirectory().toString());
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
