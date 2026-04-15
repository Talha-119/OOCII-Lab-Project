package com.fooddelivery.api;

import com.fooddelivery.data.SampleData;
import com.fooddelivery.model.MenuItem;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.service.AuthService;
import com.fooddelivery.service.CouponService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;

import java.util.List;

public class FoodDeliveryWebServiceImpl implements FoodDeliveryWebService {
    private final RestaurantService restaurantService;

    public FoodDeliveryWebServiceImpl() {
        AuthService authService = new AuthService();
        this.restaurantService = new RestaurantService();
        OrderService orderService = new OrderService();
        CouponService couponService = new CouponService();
        SampleData.load(authService, restaurantService, orderService, couponService);
    }

    @Override
    public String getRestaurantsByArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            return "Please provide a valid area.";
        }

        List<Restaurant> restaurants = restaurantService.getRestaurants();
        StringBuilder result = new StringBuilder();

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getArea() != null && restaurant.getArea().equalsIgnoreCase(area.trim())) {
                result.append("Restaurant ID: ").append(restaurant.getRestaurantId()).append("\n")
                        .append("Name: ").append(restaurant.getRestaurantName()).append("\n")
                        .append("Address: ").append(restaurant.getRestaurantAddress()).append("\n")
                        .append("Open: ").append(restaurant.getOpeningTime()).append(" - ").append(restaurant.getClosingTime()).append("\n")
                        .append("Area: ").append(restaurant.getArea()).append("\n\n");
            }
        }

        if (result.length() == 0) {
            return "No restaurants found in area: " + area;
        }

        return result.toString().trim();
    }

    @Override
    public String getMenuByRestaurant(String restaurantName) {
        if (restaurantName == null || restaurantName.trim().isEmpty()) {
            return "Please provide a valid restaurant name.";
        }

        List<Restaurant> restaurants = restaurantService.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantName() != null && restaurant.getRestaurantName().equalsIgnoreCase(restaurantName.trim())) {
                StringBuilder result = new StringBuilder();
                result.append("Menu for ").append(restaurant.getRestaurantName()).append(":\n");

                for (MenuItem item : restaurant.getMenuItems()) {
                    result.append("Food ID: ").append(item.getFoodId()).append(" | ")
                            .append(item.getFoodName()).append(" | Price: Tk ")
                            .append(item.getPrice()).append(" | Available: ")
                            .append(item.isAvailable()).append(" | Quantity: ")
                            .append(item.getQuantity()).append("\n");
                }

                return result.toString().trim();
            }
        }

        return "Restaurant not found: " + restaurantName;
    }
}
