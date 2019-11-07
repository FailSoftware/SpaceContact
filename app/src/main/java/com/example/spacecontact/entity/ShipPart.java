package com.example.spacecontact.entity;

public class ShipPart {
    private Integer totalHealth;
    private Integer currentHealth;
    private Boolean isOnFire;
    private Boolean isOnShock;

    // General constructor with all fields
    public ShipPart(Integer totalHealth, Integer currentHealth, Boolean isOnFire, Boolean isOnShock) {
        this.totalHealth = totalHealth;
        this.currentHealth = currentHealth;
        this.isOnFire = isOnFire;
        this.isOnShock = isOnShock;
    }

    //region Getters/Setters

    public Integer getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(Integer totalHealth) {
        this.totalHealth = totalHealth;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Boolean getOnFire() {
        return isOnFire;
    }

    public void setOnFire(Boolean onFire) {
        isOnFire = onFire;
    }

    public Boolean getOnShock() {
        return isOnShock;
    }

    public void setOnShock(Boolean onShock) {
        isOnShock = onShock;
    }

    //endregion
}
