package com.fooddelivery.model;

import com.fooddelivery.enums.UserRole;

public class User {
    private String userID;
    private  String name;
    private String email;
    private String phone;
    private UserRole role;
    private String password;

    public User() {}

    public User(String name, String email, String phone, UserRole role, String userID,String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.userID = userID;
        this.password = password;
    }


    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return  phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
