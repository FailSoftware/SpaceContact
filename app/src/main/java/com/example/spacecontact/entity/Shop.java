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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);




    }



    public void Shop(Ship ship){

        currentCredit = ship.getCredit();

        currentFuel = ship.getCurrentFuel();

        currentFood = ship.getCurrentFood();





    }

    public void buyRepair(View view) {



    }

    public void buyFood(View view) {


        foodFunction(currentFood,currentCredit);



    }


    public void addFuel(View view) {

        fuelFunction(currentFuel, currentCredit);

    }

    public void foodFunction(Integer currentFood, Integer currentCredit){

        Integer prizePerFood = 2;

        EditText foodToAddField = findViewById(R.id.edTFood);

        Integer foodToAdd = Integer.parseInt(foodToAddField.getText().toString());

        if(currentCredit >= (prizePerFood*foodToAdd)){

            currentFood = foodToAdd + currentFood;

            ship.setCurrentFood(currentFood);


           // return currentFood;

        }else{

            Toast.makeText(this, "You don't have enough credits to buy this", Toast.LENGTH_SHORT).show();


            ship.setCurrentFood(currentFood);

           // return currentFood;
        }


    }

    public void fuelFunction(Integer currentFuel, Integer currentCredit){

        Integer prizePerFuel = 3;

        EditText fuelToAddField = findViewById(R.id.edTFuel);

        Integer fuelToAdd = Integer.parseInt(fuelToAddField.getText().toString());

        if(currentCredit >= (prizePerFuel*fuelToAdd)){

            currentFuel = fuelToAdd + currentFuel;


            ship.setCurrentFuel(currentFuel);

            // return currentFuel;

        }else{

            Toast.makeText(this, "You don't have enough credits to buy this", Toast.LENGTH_SHORT).show();

            ship.setCurrentFuel(currentFuel);
            // return currentFuel;
        }

    }

}
