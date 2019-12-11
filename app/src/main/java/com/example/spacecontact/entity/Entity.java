package com.example.spacecontact.entity;

public abstract class Entity {
    private String name;
    private Integer totalXp;
    private Integer currentXp;
    private Integer level;
    private Integer totalHealth;
    private Integer currentHealth;

    // General constructor with all fields
    public Entity(String name, Integer totalXp, Integer currentXp, Integer level, Integer totalHealth, Integer currentHealth) {
        super();
        this.name = name;
        this.totalXp = totalXp;
        this.currentXp = currentXp;
        this.level = level;
        this.totalHealth = totalHealth;
        this.currentHealth = currentHealth;
    }

    public Entity() {
    }

    public Entity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", totalXp=" + totalXp +
                ", currentXp=" + currentXp +
                ", level=" + level +
                ", totalHealth=" + totalHealth +
                ", currentHealth=" + currentHealth +
                '}';
    }

    //region Getters/Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTotalXp() {
            return totalXp;
        }

        public void setTotalXp(Integer totalXp) {
            this.totalXp = totalXp;
        }

        public Integer getCurrentXp() {
            return currentXp;
        }

        public void setCurrentXp(Integer currentXp) {
            this.currentXp = currentXp;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

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


    //endregion
}
