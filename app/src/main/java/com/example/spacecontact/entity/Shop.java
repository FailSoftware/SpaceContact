package com.example.spacecontact.entity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spacecontact.R;

public class Shop extends AppCompatActivity {


    private Ship ship;

    private Integer currentCredit;
    private Integer currentFuel;
    private Integer currentFood;
    private Integer currentHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        currentCredit = ship.getCredit();
        currentFuel = ship.getCurrentFuel();
        currentFood = ship.getCurrentFood();
        currentHealth = ship.getCurrentHealth();


    }


    public void Shop(Ship ship) {

        currentCredit = ship.getCredit();

        currentFuel = ship.getCurrentFuel();

        currentFood = ship.getCurrentFood();


    }

    public void buyRepair(View view) {

        repairFunction();


    }

    public void buyFood(View view) {


        foodFunction(currentFood, currentCredit);


    }


    public void addFuel(View view) {

        fuelFunction(currentFuel, currentCredit);

    }

    public void foodFunction(Integer currentFood, Integer currentCredit) {

        Integer prizePerFood = 2;

        EditText foodToAddField = findViewById(R.id.edTFood);

        Integer foodToAdd = Integer.parseInt(foodToAddField.getText().toString());

        if (currentFood < ship.getTotalFood()) {


            if (currentCredit >= (prizePerFood * foodToAdd)) {

                currentFood = foodToAdd + currentFood;

                ship.setCurrentFood(currentFood);


            } else {

                Toast.makeText(this, "You don't have enough credits to buy this", Toast.LENGTH_SHORT).show();


                ship.setCurrentFood(currentFood);


            }
        } else {

            Toast.makeText(this, "You have the pantry full, don't need to buy more food", Toast.LENGTH_SHORT).show();

            ship.setCurrentFood(ship.getTotalFood());

        }

    }

    public void fuelFunction(Integer currentFuel, Integer currentCredit) {

        Integer prizePerFuel = 3;

        EditText fuelToAddField = findViewById(R.id.edTFuel);

        Integer fuelToAdd = Integer.parseInt(fuelToAddField.getText().toString());


        if (currentFuel < ship.getTotalFuel()) {

            if (currentCredit >= (prizePerFuel * fuelToAdd)) {

                currentFuel = fuelToAdd + currentFuel;


                ship.setCurrentFuel(currentFuel);


            } else {

                Toast.makeText(this, "You don't have enough credits to buy this", Toast.LENGTH_SHORT).show();

                ship.setCurrentFuel(currentFuel);

            }

        } else {

            Toast.makeText(this, "The fuel tank is full, you don't need to buy more", Toast.LENGTH_SHORT).show();

            ship.setCurrentFuel(ship.getTotalFuel());
        }


    }

    public void repairFunction(Integer currenttHealth, Integer currentCredit) {

        Integer prizeRepair = 5 + ship.getDifficulty();


    }

}
