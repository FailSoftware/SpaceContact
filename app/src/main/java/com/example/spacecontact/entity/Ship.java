package com.example.spacecontact.entity;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spacecontact.RareRewardDialog;
import com.example.spacecontact.SimpleRewardDialog;

import java.util.ArrayList;
import java.util.Random;

public class Ship extends Entity {
    //region Private variables
    private Integer difficulty;      //Number of worlds cleared, used to calculate difficulty
    private Integer credit;
    private Integer totalShield;
    private Integer currentShield;
    private Integer currentOxygen;
    private Integer currentFuel;
    private Integer totalFood;
    private Integer totalOxygen;
    private Integer totalFuel;
    private Integer currentFood;
    private Integer healthBooster;
    private Float failureIgnoreChance;
    private Weapon weapon;
    private Worker[] crew;
    private ArrayList<ShipPart> part;


//endregion

    //region Constructors

    //General constructor with all fields
    public Ship(String name, Integer totalXp, Integer currentXp, Integer level, Integer totalHealth, Integer currentHealth,
                Integer difficulty, Integer credit, Integer totalShield, Integer currentShield, Integer currentOxygen, Integer currentFuel, Integer totalFood,
                Integer totalOxygen, Integer totalFuel, Integer currentFood, Integer healthBooster, Float failureIgnoreChance,
                Weapon weapon, Worker[] crew, ArrayList<ShipPart> part) {

        super(name, totalXp, currentXp, level, totalHealth, currentHealth);
        this.difficulty = difficulty;
        this.credit = credit;
        this.currentShield = currentShield;
        this.currentOxygen = currentOxygen;
        this.currentFuel = currentFuel;
        this.totalFood = totalFood;
        this.totalOxygen = totalOxygen;
        this.totalFuel = totalFuel;
        this.totalShield = totalShield;
        this.currentFood = currentFood;
        this.healthBooster = healthBooster;
        this.failureIgnoreChance = failureIgnoreChance;
        this.weapon = weapon;
        this.crew = crew;
        this.part = part;

        //Ship's total health is equal to the sum of all the ship parts times two plus the healthBooster if applicable
        int partSum = 0;
        for (int i = 0; i < part.size(); i++) {
            partSum = part.get(i).getTotalHealth();
        }

        this.setTotalHealth(partSum * 2 + healthBooster);
    }

    //Test ship constructor
    public Ship(Context con) {
        super("TestShip", 100, 1, 1, 1, 100);
        this.difficulty = 1;
        this.credit = 1;
        this.currentShield = 1;
        this.totalOxygen = 100;
        this.currentOxygen = 1;
        this.totalFuel = 1000;
        this.currentFuel = 100;
        this.totalFood = 100;
        this.currentFood = 100;
        this.weapon = new Weapon("Basic Blaster", 20, 1, 2);
        this.healthBooster = 0;
        this.failureIgnoreChance = 10f;


        Worker[] crew = new Worker[6];
        for (int i = 0; i < 3; i++) {
            crew[i] = new Worker(con, 1);
        }

        part = new ArrayList<>();
        ShipPart body = new ShipPart("body", 100, 100, true, true, true);
        ShipPart wing = new ShipPart("wing", 100, 100, false, false, false);
        ShipPart cockpit = new ShipPart("cockpit", 100, 100, true, false, false);
        ShipPart kitchen = new ShipPart("kitchen", 100, 100, false, true, false);
        ShipPart dorm = new ShipPart("dorm", 100, 100, false, false, true);
        ShipPart engine = new ShipPart("engine", 100, 100, true, true, false);
        ShipPart forcefield = new ShipPart("forcefield", 100, 100, false, true, true);
        ShipPart storage = new ShipPart("storage", 100, 100, false, false, false);
        ShipPart infirmary = new ShipPart("infirmary", 100, 100, false, false, false);
        ShipPart armory = new ShipPart("armory", 100, 100, false, true, false);
        ShipPart bathroom = new ShipPart("bathroom", 100, 100, true, false, false);
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

    //Player ship constructor
    public Ship(Worker worker, Context con) {
        super("TestShip", 100, 1, 1, 1, 100);
        this.difficulty = 1;
        this.credit = 1;
        this.currentShield = 1;
        this.totalOxygen = 100;
        this.currentOxygen = 1;
        this.totalFuel = 1000;
        this.currentFuel = 100;
        this.totalFood = 100;
        this.currentFood = 100;
        this.weapon = new Weapon("Basic Blaster", 20, 1, 2);
        this.healthBooster = 0;
        this.failureIgnoreChance = 10f;

        Worker[] crew = new Worker[6];
        crew[0] = worker;
        for (int i = 1; i < 3; i++) {
            crew[i] = new Worker(con, 1);
        }

        part = new ArrayList<>();
        ShipPart body = new ShipPart("body", 100, 100, false, false, false);
        ShipPart wing = new ShipPart("wing", 100, 100, false, false, false);
        ShipPart cockpit = new ShipPart("cockpit", 100, 100, false, false, false);
        ShipPart kitchen = new ShipPart("kitchen", 100, 100, false, false, false);
        ShipPart dorm = new ShipPart("dorm", 100, 100, false, false, false);
        ShipPart engine = new ShipPart("engine", 100, 100, false, false, false);
        ShipPart forcefield = new ShipPart("forcefield", 100, 100, false, false, false);
        ShipPart storage = new ShipPart("storage", 100, 100, false, false, false);
        ShipPart infirmary = new ShipPart("infirmary", 100, 100, false, false, false);
        ShipPart armory = new ShipPart("armory", 100, 100, false, false, false);
        ShipPart bathroom = new ShipPart("bathroom", 100, 100, false, false, false);
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

    //Enemy ship constructor
    public Ship(int difficulty) {
        super("Enemy", 1, difficulty, difficulty, 100 + difficulty * 2, 100 + difficulty * 2);
        this.difficulty = difficulty;
        //Random credit = MIN (10 + difficulty) * 10), MAX (10 + difficulty) * 50)
        this.credit = new Random().nextInt((((((10 + difficulty) * 50) - ((10 + difficulty) * 10)) + 1) + (10 + difficulty) * 10));
        if (difficulty < 100) {
            this.totalShield = (int) (1 + difficulty / 20);
        } else {
            this.totalShield = 20;
        }
        this.currentShield = 0;
        this.totalOxygen = 100;
        this.currentOxygen = 100;
        this.totalFuel = 1000;
        this.currentFuel = new Random().nextInt(totalFuel / 5);
        this.totalFood = 100;
        this.currentFood = new Random().nextInt(totalFood / 5);
        this.weapon = new Weapon("Enemy Gun", (int) (10 + difficulty * 1.5), 0, 0);
        this.healthBooster = 0;

        if (difficulty < 90) {
            weapon.setWeaponCritChance(difficulty / 3f);
        } else {
            weapon.setWeaponCritChance(30);
        }

        if (difficulty < 100) {
            weapon.setWeaponCritChance(difficulty / 10f);
        } else {
            weapon.setWeaponCritChance(10);
        }

        this.failureIgnoreChance = 0f;

        //At difficulty 60, enemy crew caps out at 10 workers
        if (difficulty >= 60) {
            this.crew = new Worker[9];
            for (int i = 0; i < 9; i++) {
                crew[i] = new Worker(difficulty);
            }
        } else {
            this.crew = new Worker[(int) (1 + difficulty * 0.15f)];
            for (int i = 0; i < (int) (1 + difficulty * 0.15f); i++) {
                crew[i] = new Worker(difficulty);
            }
        }
        this.part = null;

        //Ship's total health is equal difficulty times 10
        this.setTotalHealth(difficulty * 10);
    }

//endregion

    //region Player Ship functions

    //Call this function when making a worker interact

    /**
     * @param action    String containing action done by a worker
     * @param w         worker realizing the action
     * @param enemyShip nullable, only used when attacking
     * @param sp        nullable, only used when repairing
     * @param injured   nullable, only used when curing
     */
    public String playerTurn(String action, Worker w, Ship enemyShip, ShipPart sp, Worker injured) {
        Log.d("Action", "Player acted, " + w.getName() + " is trying to " + action);

        String returnString = "";

        switch (action) {
            case "Attack":
                returnString = PlayerAttack(w, enemyShip);
                break;

            case "Repair":
                returnString = GeneralRepair(w);
                break;

            case "Extinguish":
                returnString = RepairExtinguish(w, sp);
                break;

            case "Weld":
                returnString = RepairWeld(w, sp);
                break;

            case "Fix":
                returnString = RepairFix(w, sp);
                break;

            case "Eat":
                returnString = WorkerEat(w);
                break;

            case "Sleep":
                returnString = WorkerSleep(w);
                break;

            case "Talk":
                returnString = WorkerTalk(w.getName());
                break;

            case "Cure":
                returnString = WorkerHeal(w, injured);

            case "Inspect":
                returnString = WorkerInspect(w.getName());
                break;

            default:
                Log.d("Combat", "Error in playerTurn action switch, Action does not exist");
                break;
        }

        w.checkStatus();

        if (w.getCurrentHealth() > 0) {
            if (w.getCurrentTurns() > 0) {
                w.setFatigue(w.getFatigue() - new Random().nextInt(30)); //Max of 30 fatigue lost each turn
                w.setCurrentTurns(w.getCurrentTurns() - 1); //Always removes a turn
            }
        }

        return returnString;
    }


    //region Worker SubFunctions
    private String WorkerInspect(String w) {
        String returnString = w + " tried to inspect something but failed cause I'm too lazy to code this properly";
        return returnString;
    }

    private String WorkerTalk(String w) {
        String returnString = w + " social anxiety did not let him talk to anyone";
        return returnString;
    }

    //Recovers 50 fatigue to worker if he's tired
    private String WorkerSleep(Worker w) {
        String returnString = "";
        if (w.getFatigue() > 100) {
            //TODO add snore sound
            w.setFatigue(100);
            returnString = w.getName() + " slept very well";
        } else {
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = w.getName() + " is not tired right now";
            //1 turn added so that it is not lost to a useless action
        }
        return returnString;
    }

    //Recovers 50 hunger to worker if there's any food in the ship
    private String WorkerEat(Worker w) {
        String returnString = "";
        if (w.getHungerLevel() < 100 && getCurrentFood() > 0) {
            if (getCurrentFood() > 10) {
                setCurrentFood(getCurrentFood() - 10);
            } else {
                setCurrentFood(0);
            }
            if (w.getHungerLevel() > 50) {
                w.setHungerLevel(100);
            } else {
                w.setHungerLevel(w.getHungerLevel() + 50);
            }
            returnString = w.getName() + " ate some food and now feels much better";

            //TODO add eat sound
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = w.getName() + " either was not hungry or did not find anything to eat";
        }
        return returnString;
    }


    private String WorkerHeal(Worker w, Worker injured) {
        String returnString = "";
        int successChance = 0;

        switch (w.getJob()) {
            case MEDIC:
                successChance = 90;
                break;

            case PILOT:
                successChance = 75;
                break;

            case RECRUIT:
                successChance = 60;
                break;

            default:
                successChance = 40;
                break;
        }

        //If successChance is greater than random %, worker successfully does the action
        if (successChance > new Random().nextInt(100)) {
            injured.removeStatuses();
            injured.setCurrentHealth(injured.getCurrentHealth() + (injured.getTotalHealth() / 8));
            if (injured.getCurrentHealth() > 100) {
                injured.setCurrentHealth(100);
            }
            returnString = w.getName() + " succesfully helped out " + injured.getName();
        } else {
            injured.setWounded(true);
            injured.setCurrentHealth(injured.getCurrentHealth() - 20);
            returnString = w.getName() + " failed aiding " + injured.getName() + " and made things even worse";
        }
        return returnString;
    }
    //endregion


    //region Ship Repair subfunctions
    //Repairs onShock part status if applicable
    private String RepairFix(Worker w, ShipPart sp) {
        String returnString = "";
        int successChance = 0;

        if (sp.getOnShock()) {
            switch (w.getJob()) {
                case MECHANIC:
                    successChance = 90;
                    break;

                case PILOT:
                    successChance = 75;
                    break;

                case RECRUIT:
                    successChance = 60;
                    break;

                default:
                    successChance = 40;
                    break;
            }

            //If successChance is greater than random %, worker successfully does the action
            if (successChance > new Random().nextInt(100)) {
                sp.setOnShock(false);
                returnString = w.getName() + " successfully fixed the short in " + sp.getPartName();
            } else {
                w.setOnShock(true);
                returnString = w.getName() + " failed fixing the short and electrocuted himself";
            }
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = "This part of the ship doesn't have a short";
        }
        return returnString;
    }

    private String RepairWeld(Worker w, ShipPart sp) {
        String returnString = "";
        int successChance = 0;

        if (sp.getPierced()) {
            switch (w.getJob()) {
                case MECHANIC:
                    successChance = 90;
                    break;

                case PILOT:
                    successChance = 75;
                    break;

                case RECRUIT:
                    successChance = 60;
                    break;

                default:
                    successChance = 40;
                    break;
            }

            //If successChance is greater than random %, worker successfully does the action
            if (successChance > new Random().nextInt(100)) {
                sp.setPierced(false);
                returnString = w.getName() + " successfully welded the hole in " + sp.getPartName();
            } else {
                w.setWounded(true);
                returnString = w.getName() + " failed fixing the short and wounded himself";
            }
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = "This part of the ship doesn't have a hole";
        }
        return returnString;
    }

    private String RepairExtinguish(Worker w, ShipPart sp) {
        String returnString = "";
        int successChance = 0;

        if (sp.getOnFire()) {
            switch (w.getJob()) {
                case FIREFIGHTER:
                    successChance = 90;
                    break;

                case PILOT:
                    successChance = 75;
                    break;

                case RECRUIT:
                    successChance = 60;
                    break;

                default:
                    successChance = 40;
                    break;
            }

            //If successChance is greater than random %, worker successfully does the action
            if (successChance > new Random().nextInt(100)) {
                sp.setOnFire(false);
                returnString = w.getName() + " successfully extinguished the fire in " + sp.getPartName();
            } else {
                w.setOnFire(true);
                returnString = w.getName() + " failed extinguishing the flames and burnt himself";
            }
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = "This part of the ship doesn't have any fire";
        }
        return returnString;
    }

    private String GeneralRepair(Worker w) {
        String returnString = "";
        int successChance = 0;
        //If true is returned, there's a status effect in a part that must be fixed before ship can be repaired
        Boolean partStatus = CheckPartStatus();

        if (!partStatus) {
            if (getCurrentHealth() < getTotalHealth()) {
                switch (w.getJob()) {
                    case MECHANIC:
                        successChance = 90;
                        break;

                    case PILOT:
                        successChance = 75;
                        break;

                    case RECRUIT:
                        successChance = 60;
                        break;

                    default:
                        successChance = 40;
                        break;
                }
                if (successChance > new Random().nextInt(100)) {
                    this.setCurrentHealth(getCurrentHealth() + (getTotalHealth() / 10));
                    returnString = w.getName() + " went through the ship fixing problems and recovering some haul power";
                } else {
                    w.setWounded(true);
                    returnString = w.getName() + " hurt himself while trying to fix the ship";
                }
            } else {
                //1 turn added so that it is not lost to a useless action
                w.setCurrentTurns(w.getCurrentTurns() + 1);
                returnString = w.getName() + " didn't find anything to fix in the ship";
            }
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = "Some parts of the ship are having serious problems, better fix those before";
        }
        return returnString;
    }
    //endregion


    private Boolean CheckPartStatus() {
        Boolean problem = false;
        for (ShipPart sp : this.getPart()) {
            if (sp.getPierced()) {
                problem = true;
            } else if (sp.getOnFire()) {
                problem = true;
            } else if (sp.getOnShock()) {
                problem = true;
            }
        }
        return problem;
    }

    private String PlayerShield(Worker w) {
        String returnString = "";
        int successChance = 0;

        switch (w.getJob()) {
            case MECHANIC:
                successChance = 90;
                break;

            case PILOT:
                successChance = 75;
                break;

            case RECRUIT:
                successChance = 60;
                break;

            default:
                successChance = 40;
                break;
        }
        if (this.getCurrentShield() < this.getTotalShield()) {
            if (successChance > new Random().nextInt(100)) {
                this.setCurrentShield(this.getCurrentShield() + 1);
                returnString = w.getName() + " turned on a shield generator";
            } else {
                w.setOnShock(true);
                returnString = w.getName() + " shocked himself with the shield generator";
            }
        } else {
            //1 turn added so that it is not lost to a useless action
            w.setCurrentTurns(w.getCurrentTurns() + 1);
            returnString = w.getName() + " did not realize all the shield generators are turned on";
        }
        return returnString;
    }

    private String PlayerAttack(Worker w, Ship enemyShip) {
        String returnString = "";
        try {

            int successChance = 0;

            switch (w.getJob()) {
                case ASSAULT:
                    successChance = 90;
                    break;

                case PILOT:
                    successChance = 75;
                    break;

                case RECRUIT:
                    successChance = 60;
                    break;

                default:
                    successChance = 40;
                    break;
            }

            if (successChance > new Random().nextInt(100)) {
                //TODO display player shot enemy
                int totalDamage = getWeapon().getWeaponPower();
                returnString = w.getName() + " shot the enemy";

                if (getWeapon().getWeaponCritChance() > new Random().nextInt(100)) {
                    //TODO display it was critical
                    totalDamage = (int) (getWeapon().getWeaponPower() * getWeapon().getWeaponCritMultiplier());
                    returnString += ". The bullet completly went through the enemy ship!";
                }

                enemyShip.setCurrentHealth(enemyShip.getCurrentHealth() - totalDamage);

            } else {
                returnString = w.getName() + " got distracted while aiming and missed the shot";
            }
        } catch (NullPointerException e) {
            returnString = w.getName() + " wondered what would happen if he stuck a potato in the antimaterial cannon";
        }
        return returnString;
    }
    //endregion


    //region Enemy AI functions

    // Enemy AI core, call this to make the enemy act
    public void EnemyAction(Ship playerShip) {
        int totalTurns = this.TurnsLeft();
        int randomTurn = 0;
        int attackChance = 500, repairChance = 700, shieldChance = 900, confusedChance = 1000;

        Log.d("Enemy", "Enemy is now starting it's turn, and has a total of [" + totalTurns + "] actions");


        for (int i = 0; i < totalTurns; i++) {
            randomTurn = new Random().nextInt(1000);
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
        RestoreTurns();
    }

    // Calculates total of turns left for a ship
    private int TurnsLeft() {
        int turnsLeft = 0;

        for (Worker worker : this.crew) {
            if (worker.getCurrentTurns() != null && worker.getCurrentTurns() > 0) {
                turnsLeft += worker.getCurrentTurns();
            }
        }
        return turnsLeft;
    }

    // Calculates total of turns left for a ship
    private void RestoreTurns() {
        for (Worker worker : this.crew) {
            worker.setCurrentTurns(worker.getTotalTurns());
        }
    }

    // Calculates attack dealt by EnemyShip to PlayerShip
    private void EnemyAttack(Ship playerShip) {
        int failChance = 3, damageDealt = 0;
        int rndAttackStat = new Random().nextInt(20);
        String descriptor = ""; //Used only in logger

        if (rndAttackStat <= failChance) {
            descriptor = "failed";
            damageDealt = 1;
        } else if (rndAttackStat == 20) {
            descriptor = "hit critically";
            damageDealt = Math.round((this.getWeapon().getWeaponPower() * this.getWeapon().getWeaponCritMultiplier()));
        } else {
            descriptor = "hit";
            damageDealt = this.getWeapon().getWeaponPower();
        }


        //Checks if player is shielded
        if (playerShip.getCurrentShield() == 0) { //Player has no currentShield and gets damaged
            Log.d("Enemy", "Current random AttackStat is [" + rndAttackStat + "], enemy " + descriptor + " and dealt [" + damageDealt + "]");
            playerShip.setCurrentHealth(playerShip.getCurrentHealth() - damageDealt);
        } else {
            if (!descriptor.equals("failed")) { //Player has currentShield and gets removed
                Log.d("Enemy", "Enemy attack was succesful, but player had currentShield");
                playerShip.setCurrentShield(playerShip.getCurrentShield() - 1);
            } else { //Player has currentShield and does not get removed
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

    // Makes enemy have 1 currentShield max
    private void EnemyShield() {
        if (this.getCurrentShield() < this.getTotalShield()) {
            Log.d("Enemy", "Enemy currentShield set to " + this.currentShield + 1);
            this.setCurrentShield(this.getCurrentShield() + 1);
        }
    }

    //TODO make enemy stupid and confused
    private void EnemyConfused() {
        Log.d("Enemy", "Enemy is confused");
    }

    //endregion

    //region Rewards
    public void battleRewards(AppCompatActivity aca, Ship enemyShip) {
        int rareItemChance = new Random().nextInt(101);
        Worker worker = null;
        Weapon weapon = null;
        int credits = enemyShip.getCredit() + 1;
        int fuel = enemyShip.getCurrentFuel() + 1;
        int food = enemyShip.getCurrentFood() + 1;

        //Worker reward
        if (rareItemChance >= 1 && rareItemChance <= 100) {
            worker = enemyShip.crew[0];

        }

        //Weapon reward
        if (rareItemChance >= 95 && rareItemChance <= 100) {
            //Possible legendary weapon
            rareItemChance = new Random().nextInt(101);
            if (rareItemChance >= 95 && rareItemChance <= 100) {
                weapon = enemyShip.getWeapon();
                weapon.setName("Legendary " + weapon.getName());
                weapon.setWeaponPower((int) (weapon.getWeaponPower() * 1.5));
                weapon.setWeaponCritChance(weapon.getWeaponCritChance() * 2);
                weapon.setWeaponCritMultiplier(weapon.getWeaponCritMultiplier() * 2);
            } else {
                weapon = enemyShip.getWeapon();
            }
        }

        Log.d("Reward", "Ship - cred:" + credits + ", fuel:" + fuel + ", food:" + food);

        if (worker != null || weapon != null) {
            if (worker != null) {
                RareRewardDialog rewardDialog = new RareRewardDialog();
                rewardDialog.setCon(aca);
                rewardDialog.setShip(this);
                rewardDialog.setWorker(worker);
                rewardDialog.setCredits(credits);
                rewardDialog.setFuel(fuel);
                rewardDialog.setFood(food);
                rewardDialog.setRarename("NEW WORKER");
                rewardDialog.setRarestat1("Name: " + worker.getName());
                rewardDialog.setRarestat2("Job: " + worker.getJob());
                rewardDialog.setRarestat3("Turns: " + worker.getTotalTurns());
                rewardDialog.show(aca.getSupportFragmentManager(), "tag");
            }
            if (weapon != null) {
                RareRewardDialog rewardDialog = new RareRewardDialog();
                rewardDialog.setCon(aca);
                rewardDialog.setShip(this);
                rewardDialog.setWeapon(weapon);
                rewardDialog.setCredits(credits);
                rewardDialog.setFuel(fuel);
                rewardDialog.setFood(food);
                rewardDialog.setRarename(weapon.getName());
                rewardDialog.setRarestat1("Fire Power: " + weapon.getWeaponPower());
                rewardDialog.setRarestat2("Critical chance: " + weapon.getWeaponCritChance());
                rewardDialog.setRarestat3("Critical multiplier: " + weapon.getWeaponCritMultiplier());
                rewardDialog.show(aca.getSupportFragmentManager(), "tag");
            }


        } else {
            SimpleRewardDialog rewardDialog = new SimpleRewardDialog();
            rewardDialog.setCon(aca);
            rewardDialog.setShip(this);
            rewardDialog.setCredits(credits);
            rewardDialog.setFuel(fuel);
            rewardDialog.setFood(food);
            rewardDialog.show(aca.getSupportFragmentManager(), "tag");
        }


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

    public Integer getCurrentShield() {
        return currentShield;
    }

    public void setCurrentShield(Integer currentShield) {
        this.currentShield = currentShield;
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

    public Integer getHealthBooster() {
        return healthBooster;
    }

    public void setHealthBooster(Integer healthBooster) {
        this.healthBooster = healthBooster;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Integer getTotalShield() {
        return totalShield;
    }

    public void setTotalShield(Integer totalShield) {
        this.totalShield = totalShield;
    }


    //endregion
}
