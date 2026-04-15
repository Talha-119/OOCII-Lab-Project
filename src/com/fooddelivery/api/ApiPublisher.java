package com.fooddelivery.api;

public class ApiPublisher {
    public static void main(String[] args) {
        System.out.println("SOAP publisher is unavailable on this JDK runtime.");
        System.out.println("Use FoodDeliveryWebServiceImpl directly or add a JAX-WS runtime dependency.");
        System.out.println("Supported methods: getRestaurantsByArea(String area), getMenuByRestaurant(String restaurantName)");
    }
}
