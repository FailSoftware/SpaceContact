package com.example.spacecontact.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.spacecontact.Game;
import com.example.spacecontact.R;

import java.util.Random;

public class Worker extends Entity {
    private Bitmap sprite;
    private Integer totalTurns;
    private Integer currentTurns;
    private Integer hungerLevel;
    private Integer fatigue;
    private Boolean isWounded;
    private Boolean isOnFire;
    private Boolean isOnShock;
    private Job job;
    public enum Job
    {
        PILOT, MECHANIC, MEDIC, FIREFIGHTER, ASSAULT, RECRUIT
    }

    // General constructor with all fields
    public Worker(String name, Integer totalXp, Integer currentXp, Integer level, Integer currentHealth, Bitmap sprite,
                  Integer totalTurns, Integer hungerLevel, Integer fatigue, Job job)
    {
        super(name, totalXp, currentXp, level, 100, currentHealth);
        this.sprite = sprite;
        this.totalTurns = totalTurns;
        this.currentTurns = totalTurns;
        this.hungerLevel = hungerLevel;
        this.fatigue = fatigue;
        this.job = job;
    }

    // Used to add a random worker to the player ship when they win a battle or hire a new worker
    //TODO change super NAME to a random name from a text file;
    //TODO Change difficulty multiplier (turns)
    public Worker(Context con)
    {
        super("John doe", 100, 1, 1, 100, 100);

        int pick = new Random().nextInt(Job.values().length);

        sprite = BitmapFactory.decodeResource(con.getResources(),R.drawable.bodyzero);
        hungerLevel = 100;
        fatigue = 100;
        isWounded = false;
        isOnFire = false;
        isOnShock = false;
        totalTurns = 1;
        if (totalTurns > 5)
        {
            totalTurns = 5;
        }
        job = Job.values()[pick];
        if (job.equals(Job.PILOT))
        {
            job = Job.RECRUIT;
        }
        currentTurns = totalTurns;

    }

    //region Getters/Setters
        public Integer getTotalTurns() {
            return totalTurns;
        }

        public Bitmap getSprite() {
            return sprite;
        }

        public void setSprite(Bitmap sprite) {
            this.sprite = sprite;
        }

        public void setTotalTurns(Integer totalTurns) {
                this.totalTurns = totalTurns;
            }

        public Integer getCurrentTurns() {
            return currentTurns;
        }

        public void setCurrentTurns(Integer currentTurns) {
            this.currentTurns = currentTurns;
        }

        public Integer getHungerLevel() {
            return hungerLevel;
        }

        public void setHungerLevel(Integer hungerLevel) {
            this.hungerLevel = hungerLevel;
        }

        public Integer getFatigue() {
            return fatigue;
        }

        public void setFatigue(Integer fatigue) {
            this.fatigue = fatigue;
        }

        public Boolean getWounded() {
            return isWounded;
        }

        public void setWounded(Boolean wounded) {
            isWounded = wounded;
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

        public Job getJob() {
            return job;
        }

        public void setJob(Job job) {
            this.job = job;
        }
    //endregion
}
