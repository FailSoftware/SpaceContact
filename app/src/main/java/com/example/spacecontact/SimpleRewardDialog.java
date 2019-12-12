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
import com.example.spacecontact.gameFunctions.SaveGame;


public class SimpleRewardDialog extends AppCompatDialogFragment {
    private int credits;
    private int fuel;
    private int food;
    private AppCompatImageView creditiv;
    private AppCompatImageView fueliv;
    private AppCompatImageView foodiv;
    private Ship ship;
    private Context con;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_simple_reward, null);

        Log.d("Reward", "Dialog - cred:" + credits + ", fuel:" + fuel + ", food:" + food);

        builder.setTitle("Rewards")
                .setView(view);

        creditiv = view.findViewById(R.id.simpleCoinImg);
        creditiv.setImageResource(R.drawable.credit);

        fueliv = view.findViewById(R.id.simpleFuelImg);
        fueliv.setImageResource(R.drawable.fuel);

        foodiv = view.findViewById(R.id.simpleFoodImg);
        foodiv.setImageResource(R.drawable.food);

        TextView creditet = (TextView) view.findViewById(R.id.simpleCoinAmmount);
        creditet.setText("Credits: " + credits);
        TextView fuelet = (TextView) view.findViewById(R.id.simpleFuelAmmount);
        fuelet.setText("Fuel: " + fuel);
        TextView foodet = (TextView) view.findViewById(R.id.simpleFoodAmmount);
        foodet.setText("Food: " + food);

        Ship ship = getShip();
        //TODO onClickListener add to ship
        Button btnConfirm = (Button) view.findViewById(R.id.btnSimpleConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
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

                SaveGame sg = new SaveGame(ship);
                sg.run();



//                Gson gson = new Gson();
//                String json = gson.toJson(ship);
//                intent.putExtra("json", json);

                Intent i = new Intent(con, Game.class);
                con.startActivity(i);

            }
        });


        return builder.create();
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

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setCon(Context con) {
        this.con = con;
    }

    public Ship getShip() {
        return ship;
    }

    public Context getCon() {
        return con;
    }
}
