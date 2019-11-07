package com.example.spacecontact.entity;

import java.util.ArrayList;

public class Ship extends Entity {
    private Integer world;      //Number of worlds cleared, used to calculate difficulty
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

    private ArrayList<Worker> crew;
    private ArrayList<ShipPart> part;

    //General constructor with all fields
    public Ship(Integer world, Integer credit, String name, Integer totalXp, Integer currentXp, Integer level,
                Integer currentHealth, Integer shield, Integer currentOxygen, Integer currentFuel, Integer currentFood,
                Integer healthBooster, Integer weaponBooster, Float criticalChance, Float failureIgnoreChance,
                ArrayList<Worker> crew, ArrayList<ShipPart> part)
    {
        super(name, totalXp, currentXp, level, 100, currentHealth);
        this.world = world;
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


    //region Getters/Setters\

    public Integer getWorld() {
        return world;
    }

    public void setWorld(Integer world) {
        this.world = world;
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

    public ArrayList<Worker> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Worker> crew) {
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
