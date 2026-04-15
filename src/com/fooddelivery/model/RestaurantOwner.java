package com.fooddelivery.model;

import com.fooddelivery.enums.UserRole;

public class RestaurantOwner extends User{
    private String restaurantId;

    public RestaurantOwner(String  restaurantId,String name, String email, String phone,String userID,String password) {
        super(name,email,phone, UserRole.RESTAURANT_OWNER,userID,password);
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
