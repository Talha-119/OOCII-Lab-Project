package com.fooddelivery.service;

import com.fooddelivery.enums.OrderStatus;
import com.fooddelivery.model.Coupon;
import com.fooddelivery.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Order placeOrder(Order order){
        applyRepeatCoupon(order);
        addOrder(order);
        return order;
    }

    public List<Order> getAllOrders(){
        return orders;
    }

    public Order getOrderById(String orderId){
        for(Order order : orders){
            if(order.getOrderId().equals(orderId)){
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(String orderId, OrderStatus status){
        Order order = getOrderById(orderId);
        if(order != null){
            order.setStatus(status);
        }
    }

    public boolean hasCustomerOrdered(String customerId,String restaurantId){
        for(Order order : orders){
            if(order.getCustomerId().equals(customerId) && order.getRestaurantId().equals(restaurantId)){
                return true;
            }
        }
        return false;
    }

    public Coupon isRepeatCustomercoupon(String customerId, String restaurantId){
        if(hasCustomerOrdered(customerId, restaurantId)){
            return new Coupon("coupon10",10.00,true);
        }
        return null;
    }

    public void applyRepeatCoupon(Order order){
        Coupon coupon = isRepeatCustomercoupon(order.getCustomerId(),order.getRestaurantId());
        if(coupon != null){
            order.setCoupon(coupon);
        }
    }

}
