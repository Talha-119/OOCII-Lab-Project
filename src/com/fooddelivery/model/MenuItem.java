package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String restaurantId;
    private String foodId;
    private String foodName;
    private Double price;
    private boolean available;
    private int quantity;
    private List<String> customizationOptions;

    public MenuItem(String restaurantId, String foodId, String foodName, Double price, boolean available, int quantity) {
        this.restaurantId = restaurantId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.available = available;
        this.quantity = quantity;
        this.customizationOptions = new ArrayList<>();
    }

    public void addCustomization(String option){
        this.customizationOptions.add(option);
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    public String getFoodId() {
        return foodId;
    }
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
