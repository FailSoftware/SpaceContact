package com.example.spacecontact.entity;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Ship extends Entity {
    private Integer difficulty;      //Number of worlds cleared, used to calculate difficulty
    private Integer credit;
    private Integer shield;
    private Integer currentOxygen;
    private Integer currentFuel;

    private Integer totalFood;
    private Integer totalOxygen;
    private Integer totalFuel;

    private Integer currentFood;
    private Integer weaponPower;

    private Integer healthBooster;
    private Integer weaponBooster;

    private Float criticalChance;
    private Float failureIgnoreChance;

    private Worker[] crew;
    private ArrayList<ShipPart> part;

    //General constructor with all fields
    public Ship(Integer world, Integer credit, String name, Integer totalXp, Integer currentXp, Integer level,
                Integer currentHealth, Integer shield, Integer currentOxygen, Integer currentFuel, Integer currentFood,
                Integer healthBooster, Integer weaponBooster, Float criticalChance, Float failureIgnoreChance,
                Worker[] crew, ArrayList<ShipPart> part) {
        super(name, totalXp, currentXp, level, 100 * healthBooster, currentHealth);
        this.difficulty = world;
        this.credit = credit;
        this.shield = shield;
        this.totalOxygen = 100;
        this.currentOxygen = currentOxygen;
        this.totalFuel = 1000;
        this.currentFuel = currentFuel;
        this.totalFood = 100;
        this.currentFood = currentFood;
        this.weaponPower = 20;
        this.healthBooster = healthBooster;
        this.weaponBooster = weaponBooster;
        this.criticalChance = criticalChance;
        this.failureIgnoreChance = failureIgnoreChance;
        this.crew = crew;
        this.part = part;
    }

    //Test ship
    public Ship(Context con) {
        super("TestShip", 100, 1, 1, 100 * 0, 100);
        this.difficulty = 1;
        this.credit = 1;
        this.shield = 1;
        this.totalOxygen = 100;
        this.currentOxygen = 1;
        this.totalFuel = 1000;
        this.currentFuel = 100;
        this.totalFood = 100;
        this.currentFood = 100;
        this.weaponPower = 20;
        this.healthBooster = 0;
        this.weaponBooster = 0;
        this.criticalChance = 10f;
        this.failureIgnoreChance = 10f;

        Worker[] crew = new Worker[9];
        crew = new Worker[6];
        crew[0] = new Worker(con);
        crew[1] = new Worker(con);
        crew[2] = new Worker(con);
        crew[3] = new Worker(con);
        crew[4] = new Worker(con);
        crew[5] = new Worker(con);

        part = new ArrayList<>();
        ShipPart body = new ShipPart(100, 100, false, false, false);
        ShipPart wing = new ShipPart(100, 100, false, false, false);
        ShipPart cockpit = new ShipPart(100, 100, false, false, false);
        ShipPart kitchen = new ShipPart(100, 100, false, false, false);
        ShipPart dorm = new ShipPart(100, 100, false, false, false);
        ShipPart engine = new ShipPart(100, 100, false, false, false);
        ShipPart forcefield = new ShipPart(100, 100, false, false, false);
        ShipPart storage = new ShipPart(100, 100, false, false, false);
        ShipPart infirmary = new ShipPart(100, 100, false, false, false);
        ShipPart armory = new ShipPart(100, 100, false, false, false);
        ShipPart bathroom = new ShipPart(100, 100, false, false, false);
        part.add(body);
        part.add(wing);
        part.add(cockpit);
        part.add(kitchen);
        part.add(dorm);
        part.add(engine);
        part.add(forcefield);
        part.add(storage);
        part.add(infirmary);
        part.add(armory);
        part.add(bathroom);

        this.crew = crew;
        this.part = part;
    }


    //region Enemy AI

    // Enemy AI core, call this to make the enemy act
    public void EnemyAction(Ship playerShip) {
        int totalTurns = this.TurnsLeft();
        int randomTurn = 0;
        int attackChance = 500, repairChance = 700, shieldChance = 900, confusedChance = 1000;

        Log.d("Enemy", "Enemy is now starting it's turn, and has a total of [" + totalTurns + "] actions");


        for (int i = 0; i < totalTurns; i++) {
            randomTurn = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
            Log.d("Enemy", "Current random turn is [" + randomTurn + "]");

            //Attack
            if (randomTurn < attackChance) {
                Log.d("Enemy", "    >Enemy is now attacking");
                this.EnemyAttack(playerShip);
            }

            //Repair
            if (randomTurn > attackChance && randomTurn < repairChance) {
                Log.d("Enemy", "    >Enemy is now repairing");
                EnemyRepair();
            }

            //Shield
            if (randomTurn > repairChance && randomTurn < confusedChance) {
                Log.d("Enemy", "    >Enemy is now shielding");
                EnemyShield();
            }
        }
    }

    // Calculates total of turns left for a ship
    private int TurnsLeft() {
        int turnsLeft = 0;
        Worker[] enemyCrew = this.crew;

        for (Worker worker : enemyCrew) {
            if (worker.getCurrentTurns() != null && worker.getCurrentTurns() > 0) {
                turnsLeft += worker.getCurrentTurns();
            }
        }
        return turnsLeft;
    }

    // Calculates attack dealt by EnemyShip to PlayerShip
    private void EnemyAttack(Ship playerShip) {
        int failChance = 3, damageDealt = 0;
        float criticalMultiplier = 1.5f;
        int rndAttackStat = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        String descriptor = ""; //Used only in logger

        if (rndAttackStat <= failChance) {
            descriptor = "failed";
            damageDealt = 1;
        } else if (rndAttackStat >= criticalChance) {
            descriptor = "hit critically";
            damageDealt = Math.round((this.getWeaponPower() * this.getWeaponBooster()) * criticalMultiplier);
        } else {
            descriptor = "hit";
            damageDealt = this.getWeaponPower() * this.getWeaponBooster();
        }


        //Checks if player is shielded
        if (playerShip.getShield() == 0) { //Player has no shield and gets damaged
            Log.d("Enemy", "Current random AttackStat is [" + rndAttackStat + "], enemy " + descriptor + " and dealt [" + damageDealt + "]");
            playerShip.setCurrentHealth(playerShip.getCurrentHealth() - damageDealt);
        } else {
            if (!descriptor.equals("failed")) { //Player has shield and gets removed
                Log.d("Enemy", "Enemy attack was succesful, but player had shield");
                playerShip.setShield(playerShip.getShield() - 1);
            } else { //Player has shield and does not get removed
                Log.d("Enemy", "Enemy failed and the player was shielded, nothing happened");
            }
        }
    }

    // Calculates ammount of health EnemyShip recovers
    private void EnemyRepair() {
        int healthRecovered = 0, repairPercentage = 5;

        //If health is at max
        if (this.getCurrentHealth() >= this.getTotalHealth()) {
            Log.d("Enemy", "Enemy tried to recover health, but already had max health. Calling EnemyConfused()");
            EnemyConfused();

        } else { //Calculates health
            int rep = (repairPercentage / 100) * getTotalHealth();

            //Caps health at max
            if (this.getCurrentHealth() + rep > this.getTotalHealth()) {
                Log.d("Enemy", "Enemy recovere all of it's health");
                this.setCurrentHealth(this.getTotalHealth());
            } else { //Recovers health
                Log.d("Enemy", "Enemy recovers [" + rep + "HP (" + repairPercentage + "%)]");
                this.setCurrentHealth(getCurrentHealth() + rep);
            }
        }
    }

    // Makes enemy have 1 shield max
    private void EnemyShield() {
        // Todo Add a max shield variable (?

        if (this.getShield() < 1) {
            Log.d("Enemy", "Enemy shield set to 1");
            this.setShield(1);
        }
    }

    //TODO make enemy stupid and confused
    private void EnemyConfused() {
        Log.d("Enemy", "Enemy is confused");
    }

    //endregion

    //region Getters/Setters\

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getShield() {
        return shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    public Integer getCurrentOxygen() {
        return currentOxygen;
    }

    public void setCurrentOxygen(Integer currentOxygen) {
        this.currentOxygen = currentOxygen;
    }

    public Integer getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(Integer currentFuel) {
        this.currentFuel = currentFuel;
    }

    public Integer getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(Integer totalFood) {
        this.totalFood = totalFood;
    }

    public Integer getTotalOxygen() {
        return totalOxygen;
    }

    public void setTotalOxygen(Integer totalOxygen) {
        this.totalOxygen = totalOxygen;
    }

    public Integer getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(Integer totalFuel) {
        this.totalFuel = totalFuel;
    }

    public Integer getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(Integer currentFood) {
        this.currentFood = currentFood;
    }

    public Integer getWeaponPower() {
        return weaponPower;
    }

    public void setWeaponPower(Integer weaponPower) {
        this.weaponPower = weaponPower;
    }

    public Integer getHealthBooster() {
        return healthBooster;
    }

    public void setHealthBooster(Integer healthBooster) {
        this.healthBooster = healthBooster;
    }

    public Integer getWeaponBooster() {
        return weaponBooster;
    }

    public void setWeaponBooster(Integer weaponBooster) {
        this.weaponBooster = weaponBooster;
    }

    public Float getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(Float criticalChance) {
        this.criticalChance = criticalChance;
    }

    public Float getFailureIgnoreChance() {
        return failureIgnoreChance;
    }

    public void setFailureIgnoreChance(Float failureIgnoreChance) {
        this.failureIgnoreChance = failureIgnoreChance;
    }

    public Worker[] getCrew() {
        return crew;
    }

    public void setCrew(Worker[] crew) {
        this.crew = crew;
    }

    public ArrayList<ShipPart> getPart() {
        return part;
    }

    public void setPart(ArrayList<ShipPart> part) {
        this.part = part;
    }

    //endregion
}
