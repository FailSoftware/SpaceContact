package com.example.spacecontact.entity;

import java.util.Date;

public class User {
    private Integer userid;
    private Boolean developer;
    private String fullname;
    private String username;
    private String description;
    private Date dateofbirth;


    // General constructor with all fields
    public User(Integer userid, Boolean developer, String fullname, String username, String description, Date dateofbirth) {
        this.userid = userid;
        this.developer = developer;
        this.fullname = fullname;
        this.username = username;
        this.description = description;
        this.dateofbirth = dateofbirth;
    }

    //region Getters / Setters

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Boolean getDeveloper() {
        return developer;
    }

    public void setDeveloper(Boolean developer) {
        this.developer = developer;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    //endregion
}
