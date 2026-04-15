package com.fooddelivery.model;

import com.fooddelivery.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<String> orderIds;

    public Customer(String name, String email, String phone,String userID,String password) {
        super(name,email,phone,UserRole.CUSTOMER,userID,password);
        this.orderIds = new ArrayList<>();
    }

    public void addOrder(String orderId) {
        this.orderIds.add(orderId);
    }

    public List<String> getOrderIds() {
        return orderIds;
    }
}