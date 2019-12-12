package com.example.spacecontact.gameFunctions;

import android.os.Environment;
import android.util.Log;

import com.example.spacecontact.Preferences;
import com.example.spacecontact.entity.Ship;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadGame implements Runnable{
    private String json;
    private volatile Ship ship;

    public LoadGame(){
    }

    @Override
    public void run(){
        try {
            Gson gson = new Gson();
            String json = "";
            StringBuilder sb = new StringBuilder();
            Log.d("save", "load >> " + Environment.getExternalStorageDirectory().toString());

            FileInputStream fis = new FileInputStream(new File(Environment.getExternalStorageDirectory(), "sps.json"));
            if (fis != null){
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String tempStr = null;
                while ((tempStr = br.readLine()) != null){
                    sb.append(tempStr + "\n");
                }
                fis.close();
            }

            json = sb.toString();
            ship = gson.fromJson(json, Ship.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Ship getShip() {
        return ship;
    }
}
