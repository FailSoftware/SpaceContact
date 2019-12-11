package com.example.spacecontact.entity;

import java.io.Serializable;

public class Weapon extends Entity implements Serializable {
    //ProxyBitmap weaponSprite;
    int weaponPower;
    float weaponCritChance;
    float weaponCritMultiplier;

    public Weapon(String name, int weaponPower, float weaponCritChance, float weaponCritMultiplier) {
        super(name);
        this.weaponPower = weaponPower;
        this.weaponCritChance = weaponCritChance;
        this.weaponCritMultiplier = weaponCritMultiplier;
    }

//    public ProxyBitmap getWeaponSprite() {
//        return weaponSprite;
//    }
//
//    public void setWeaponSprite(ProxyBitmap weaponSprite) {
//        this.weaponSprite = weaponSprite;
//    }

    public int getWeaponPower() {
        return weaponPower;
    }

    public void setWeaponPower(int weaponPower) {
        this.weaponPower = weaponPower;
    }

    public float getWeaponCritChance() {
        return weaponCritChance;
    }

    public void setWeaponCritChance(float weaponCritChance) {
        this.weaponCritChance = weaponCritChance;
    }

    public float getWeaponCritMultiplier() {
        return weaponCritMultiplier;
    }

    public void setWeaponCritMultiplier(float weaponCritMultiplier) {
        this.weaponCritMultiplier = weaponCritMultiplier;
    }
}
