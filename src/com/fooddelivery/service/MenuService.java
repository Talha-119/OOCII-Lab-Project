package com.fooddelivery.service;

import com.fooddelivery.model.MenuItem;
import com.fooddelivery.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    public void addMenuItem(Restaurant restaurant, MenuItem menuItem) {
        restaurant.addMenuItem(menuItem);
    }

    public List<MenuItem> getMenuItems(Restaurant restaurant){
        List<MenuItem> menuitems = new ArrayList<>();

        for(MenuItem item : restaurant.getMenuItems()){
            if(item.isAvailable()){
                menuitems.add(item);
            }
        }

        return menuitems;
    }

    public MenuItem findMenuItem(Restaurant restaurant,String  foodItem){
        for(MenuItem item : restaurant.getMenuItems()){
            if(item.getFoodId().equals(foodItem)){
                return item;
            }
        }
        return null;
    }

    public MenuItem getMenuItemByRestaurantAndFoodId(String restaurantId, String foodId, RestaurantService restaurantService) {
        Restaurant restaurant = restaurantService.findRestaurantWithId(restaurantId);
        if (restaurant == null) {
            return null;
        }
        for (MenuItem item : restaurant.getMenuItems()) {
            if (item.getFoodId().equals(foodId)) {
                return item;
            }
        }
        return null;
    }
}
