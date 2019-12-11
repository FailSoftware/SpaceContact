package com.example.spacecontact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.spacecontact.entity.Ship;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

// Mis intentos a las 7 de la mañana tras trasnochar tratando de hacer que la app no pete al intentar cargar una partida

public class GameSaveManager{
    private Ship ship;
    private String json;
    private Context con;


    public GameSaveManager(Context con, Ship ship) {
        this.con = con;
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }


    public Runnable SaveGame = new Runnable() {
        @Override
        public void run() {
            try {
                Gson gson = new Gson();
                json = gson.toJson(ship);
                File f = new File("./save.json");
                if (!f.exists()){
                    f.createNewFile();
                }
                FileOutputStream fos = con.openFileOutput("save.json", Context.MODE_PRIVATE);
                fos.write(json.getBytes(), 0, json.length());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public Runnable LoadGame = new Runnable() {
        @Override
        public void run() {
            try {
                Gson gson = new Gson();
                JsonReader jr = new JsonReader(new FileReader("save.json"));
                Ship temp = gson.fromJson(jr, Ship.class);
                setShip(temp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    };
}

