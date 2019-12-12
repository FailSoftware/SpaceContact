package com.example.spacecontact.entity;

import android.graphics.Bitmap;
import android.os.Parcel;

import java.util.ArrayList;

public class Npc extends Entity{
    private ArrayList<String> lore;
    private Bitmap npc;

    public Npc(String name, Integer totalXp, Integer currentXp, Integer level, Integer totalHealth, Integer currentHealth, ArrayList<String> lore, Bitmap npc) {
        super("Pruebas");
        this.lore = lore;
        this.npc = npc;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public void setLore(ArrayList<String> lore) {
        this.lore = lore;
    }

    public Bitmap getNpc() {
        return npc;
    }

    public void setNpc(Bitmap npc) {
        this.npc = npc;
    }

}
