package com.example.spacecontact.gameFunctions;

public class CustomTag {
    String action;
    String position;

    public CustomTag(){
        action = null;
        position = null;
    }

    public CustomTag(String action, String position) {
        this.action = action;
        this.position = position;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
