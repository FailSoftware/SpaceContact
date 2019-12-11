package com.example.spacecontact.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.util.Date;

public class User implements Parcelable {
    private Integer userid;
    private Boolean developer;
    private String fullname;
    private String username;
    private String description;
    private LocalDate dateofbirth;
    private Worker pilot;
    private String id;


    // General constructor with all fields
    public User(Integer userid, Boolean developer, String fullname, String username, String description, LocalDate dateofbirth, Worker pilot) {
        this.userid = userid;
        this.developer = developer;
        this.fullname = fullname;
        this.username = username;
        this.description = description;
        this.dateofbirth = dateofbirth;
        this.pilot = pilot;
    }

    //region Getters / Setters

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            userid = null;
        } else {
            userid = in.readInt();
        }
        byte tmpDeveloper = in.readByte();
        developer = tmpDeveloper == 0 ? null : tmpDeveloper == 1;
        fullname = in.readString();
        username = in.readString();
        description = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Worker getPilot() {
        return pilot;
    }

    public void setPilot(Worker pilot) {
        this.pilot = pilot;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userid);
        }
        dest.writeByte((byte) (developer == null ? 0 : developer ? 1 : 2));
        dest.writeString(fullname);
        dest.writeString(username);
        dest.writeString(description);
    }

    //endregion
}
