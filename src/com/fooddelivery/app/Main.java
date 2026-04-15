package com.fooddelivery.app;

import com.fooddelivery.data.SampleData;
import com.fooddelivery.service.AuthService;
import com.fooddelivery.service.CouponService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;
import com.fooddelivery.ui.FoodDeliveryUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        RestaurantService restaurantService = new RestaurantService();
        OrderService orderService = new OrderService();
        CouponService couponService = new CouponService();

        SampleData.load(authService, restaurantService, orderService, couponService);

        SwingUtilities.invokeLater(() ->
                new FoodDeliveryUI(authService, restaurantService, orderService, couponService).setVisible(true)
        );
    }
}
