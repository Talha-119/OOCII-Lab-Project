package com.fooddelivery.model;

import com.fooddelivery.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private String restaurantId;
    private List<OrderItem> orderItems;
    private OrderStatus status;
    private Coupon coupon;

    public Order(String orderId, String customerId, String restaurantId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderItems = new ArrayList<>();
        this.status = OrderStatus.PLACED;
    }

    public void addItem(OrderItem item){
        this.orderItems.add(item);
    }

    public double calculateTotal(){
        double total = 0;
        for(OrderItem orderItem : orderItems){
            total += orderItem.getUnitPrice()*orderItem.getQuantity();
        }
        return total;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double calculateFinalTotal(){
        if(coupon==null || !coupon.isActive()){
            return calculateTotal();
        }
        double finalTotal =  0;
        finalTotal = coupon.applyCoupon(calculateTotal());
        return finalTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
