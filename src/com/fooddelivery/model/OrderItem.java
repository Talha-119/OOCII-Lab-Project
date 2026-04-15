package com.fooddelivery.model;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private String foodId;
    private String foodName;
    private double unitPrice;
    private int quantity;
    private List<String> selectedCustomizations;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.foodId = menuItem.getFoodId();
        this.foodName = menuItem.getFoodName();
        this.unitPrice = menuItem.getPrice();
        this.quantity = quantity;
        this.selectedCustomizations = new ArrayList<>();
    }


    public void addSelectedCustomization(String option){
        this.selectedCustomizations.add(option);
    }
    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
