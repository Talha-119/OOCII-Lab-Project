package com.fooddelivery.api;

public interface FoodDeliveryWebService {
    String getRestaurantsByArea(String area);

    String getMenuByRestaurant(String restaurantName);
}
