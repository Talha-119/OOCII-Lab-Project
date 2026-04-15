package com.fooddelivery.service;

import com.fooddelivery.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private List<Restaurant> restaurants;

    public RestaurantService() {
        restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant findRestaurantWithId(String restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId().equals(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    public List<Restaurant> findRestaurantWithArea(String area){
        List<Restaurant> matchedRestaurant = new ArrayList<>();
        if (area == null || area.trim().isEmpty()) {
            return matchedRestaurant;
        }
        String normalizedArea = area.trim();
        for (Restaurant restaurant : restaurants) {
            if(restaurant.getArea() != null && restaurant.getArea().equalsIgnoreCase(normalizedArea)){
                matchedRestaurant.add(restaurant);
            }
        }
        return matchedRestaurant;
    }

}
