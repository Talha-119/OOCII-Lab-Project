package com.fooddelivery.model;

public class Coupon {
    private String  code;
    private double discountPercent;
    private boolean active;

    public Coupon(String code, double discountPercent, boolean active) {
        this.code = code;
        this.discountPercent = discountPercent;
        this.active = active;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public double getDiscountPrice() {
        return discountPercent;
    }
    public void setDiscountPrice(double discountPrice) {
        this.discountPercent = discountPrice;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public double applyCoupon(double total){
        double finalAmount = 0;
        finalAmount = total - (total * (discountPercent/100));
        return finalAmount;
    }
}
