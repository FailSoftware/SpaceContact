package com.example.spacecontact;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.spacecontact.entity.Ship;
import com.example.spacecontact.entity.Weapon;
import com.example.spacecontact.entity.Worker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RareRewardDialog extends AppCompatDialogFragment {
    private int credits;
    private int fuel;
    private int food;
    private String rarename;
    private String rarestat1;
    private String rarestat2;
    private String rarestat3;
    private Context con;
    private Ship ship;
    private Worker worker;
    private Weapon weapon;

    private AppCompatImageView creditiv;
    private AppCompatImageView fueliv;
    private AppCompatImageView foodiv;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_rare_reward, null);

        Log.d("RARE Reward", "Dialog - cred:" + credits + ", fuel:" + fuel + ", food:" + food);

        builder.setTitle("Rewards")
                .setView(view);

        creditiv = view.findViewById(R.id.rareCoinImg);
        creditiv.setImageResource(R.drawable.credit);

        fueliv = view.findViewById(R.id.rareFuelImg);
        fueliv.setImageResource(R.drawable.fuel);

        foodiv = view.findViewById(R.id.rareFoodImg);
        foodiv.setImageResource(R.drawable.food);

        TextView creditet = (TextView) view.findViewById(R.id.rareCoinAmmount);
        creditet.setText("Credits: " + credits);
        TextView fuelet = (TextView) view.findViewById(R.id.rareFuelAmmount);
        fuelet.setText("Fuel: " + fuel);
        TextView foodet = (TextView) view.findViewById(R.id.rareFoodAmmount);
        foodet.setText("Food: " + food);

        TextView rarenametv = (TextView) view.findViewById(R.id.rareName);
        rarenametv.setText(rarename);
        TextView rarestat1tv = (TextView) view.findViewById(R.id.rareStat1);
        rarestat1tv.setText(rarestat1);
        TextView rarestat2tv = (TextView) view.findViewById(R.id.rareStat2);
        rarestat2tv.setText(rarestat2);
        TextView rarestat3tv = (TextView) view.findViewById(R.id.rareStat3);
        rarestat3tv.setText(rarestat3);

        final Button btnAccept = (Button) view.findViewById(R.id.btnRareAccept);
        Button btnDiscard = (Button) view.findViewById(R.id.btnRareDiscard);

        //TODO onClickListener add to ship
        if (worker != null) {
            for (int i = 0; i < ship.getCrew().length; i++) {
                if (ship.getCrew()[i] == null) {
                    //TODO add string
                    btnAccept.setText("Add crew");
                }
            }
        }

        btnAccept.setOnClickListener(new View.OnClickListener() {
            Ship ship = getShip();
            Context con = getCon();

            @Override
            public void onClick(View v) {
                if (btnAccept.getText().equals("Add crew")) {
                    for (int i = 0; i < ship.getCrew().length; i++) {
                        if (ship.getCrew()[i] == null) {
                            ship.getCrew()[i] = worker;
                        }
                    }
                } else if (worker != null) {
                    //TODO make a worker replace function
                } else if (weapon != null) {
                    ship.setWeapon(weapon);
                }

                ship.setCredit(ship.getCredit() + credits);
                if (ship.getCurrentFuel() < ship.getCurrentFuel()) {
                    ship.setCurrentFuel(ship.getCurrentFuel() + fuel);
                }
                if (ship.getTotalFood() < ship.getCurrentFood()) {
                    ship.setCurrentFood(ship.getCurrentFood() + food);
                }
                GsonBuilder gbilder = new GsonBuilder();
                gbilder.setPrettyPrinting();
                Gson gson = gbilder.create();
                String json = gson.toJson(ship);
                Log.d("json", json);
                Intent intent = new Intent(con, Game.class);
                intent.putExtra("json", json);
                con.startActivity(intent);
            }
        });

        btnDiscard.setOnClickListener(new View.OnClickListener() {
            Ship ship = getShip();
            Context con = getCon();

            @Override
            public void onClick(View v) {
                ship.setCredit(ship.getCredit() + credits);
                if (ship.getCurrentFuel() < ship.getCurrentFuel()) {
                    ship.setCurrentFuel(ship.getCurrentFuel() + fuel);
                }
                if (ship.getTotalFood() < ship.getCurrentFood()) {
                    ship.setCurrentFood(ship.getCurrentFood() + food);
                }
                GsonBuilder gbilder = new GsonBuilder();
                gbilder.setPrettyPrinting();
                Gson gson = gbilder.create();
                String json = gson.toJson(ship);
                Log.d("json", json);
                Intent intent = new Intent(con, Game.class);
                intent.putExtra("json", json);
                con.startActivity(intent);
            }
        });


        return builder.create();
    }

    public void setRarename(String rarename) {
        this.rarename = rarename;
    }

    public void setRarestat1(String rarestat1) {
        this.rarestat1 = rarestat1;
    }

    public void setRarestat2(String rarestat2) {
        this.rarestat2 = rarestat2;
    }

    public void setRarestat3(String rarestat3) {
        this.rarestat3 = rarestat3;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public Context getCon() {
        return con;
    }

    public void setCon(Context con) {
        this.con = con;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
