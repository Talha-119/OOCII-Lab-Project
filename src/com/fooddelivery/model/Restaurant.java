package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String openingTime;
    private String closingTime;
    private String ownerId;
    private String area;
    private List<MenuItem> menuItems;

    public Restaurant(String restaurantId, String restaurantName, String restaurantAddress, String openingTime, String closingTime,String ownerId,String area) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ownerId = ownerId;
        this.area = area;
        this.menuItems = new ArrayList<>();
    }

    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantAddress() {
        return restaurantAddress;
    }
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }
    public String getOpeningTime() {
        return openingTime;
    }
    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }
    public String getClosingTime() {
        return closingTime;
    }
    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
    public String getOwnerID() {
        return ownerId;
    }
    public void setOwnerID(String ownerID) {
        ownerId = ownerID;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
