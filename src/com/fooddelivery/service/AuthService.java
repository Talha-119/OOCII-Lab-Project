package com.fooddelivery.service;

import com.fooddelivery.model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users;

    public AuthService() {
        this.users = new ArrayList<>();
    }

    public boolean registerUser(User user) {
        String email = user.getEmail();
        if(!emailExists(email)) {
            users.add(user);
            return true;
        }
        return false;
    }

    public User login(String email, String password) {
        for(User user : users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


}
