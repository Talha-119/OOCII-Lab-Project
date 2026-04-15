package com.fooddelivery.data;

import com.fooddelivery.model.*;

import com.fooddelivery.service.*;
public class SampleData {
    public static void load(AuthService authService,
                            RestaurantService restaurantService,
                            OrderService orderService,
                            CouponService couponService) {

        Customer c1 = new Customer("Irtiaj", "irtiaj@gmail.com", "01878000001", "cus01", "pass1");
        Customer c2 = new Customer("Rahim", "rahim@gmail.com", "01878000002", "cus02", "pass2");
        Customer c3 = new Customer("Karim", "karim@gmail.com", "01878000003", "cus03", "pass3");

        authService.registerUser(c1);
        authService.registerUser(c2);
        authService.registerUser(c3);

        RestaurantOwner o1 = new RestaurantOwner("res01", "Owner One", "owner1@gmail.com", "01711000001", "own01", "ownerpass1");
        RestaurantOwner o2 = new RestaurantOwner("res02", "Owner Two", "owner2@gmail.com", "01711000002", "own02", "ownerpass2");
        RestaurantOwner o3 = new RestaurantOwner("res03", "Owner Three", "owner3@gmail.com", "01711000003", "own03", "ownerpass3");

        authService.registerUser(o1);
        authService.registerUser(o2);
        authService.registerUser(o3);

        Restaurant r1 = new Restaurant("res01", "Burger Town", "Gazipur Chowrasta", "10:00", "22:00", "own01", "Gazipur");
        Restaurant r2 = new Restaurant("res02", "Pizza Hub", "Uttara Sector 10", "11:00", "23:00", "own02", "Uttara");
        Restaurant r3 = new Restaurant("res03", "Kacchi House", "Dhanmondi 27", "09:00", "23:30", "own03", "Dhanmondi");

        MenuItem m1 = new MenuItem("res01", "food01", "Burger", 150.0, true, 20);
        MenuItem m2 = new MenuItem("res01", "food02", "Fries", 80.0, true, 30);

        MenuItem m3 = new MenuItem("res02", "food03", "Chicken Pizza", 500.0, true, 15);
        MenuItem m4 = new MenuItem("res02", "food04", "Cold Drinks", 40.0, true, 25);

        MenuItem m5 = new MenuItem("res03", "food05", "Kacchi", 300.0, true, 10);
        MenuItem m6 = new MenuItem("res03", "food06", "Borhani", 60.0, true, 20);

        r1.addMenuItem(m1);
        r1.addMenuItem(m2);

        r2.addMenuItem(m3);
        r2.addMenuItem(m4);

        r3.addMenuItem(m5);
        r3.addMenuItem(m6);

        restaurantService.addRestaurant(r1);
        restaurantService.addRestaurant(r2);
        restaurantService.addRestaurant(r3);

        Coupon cpn1 = new Coupon("SAVE10", 10.0, true);
        Coupon cpn2 = new Coupon("WELCOME15", 15.0, true);

        couponService.addCoupon(cpn1);
        couponService.addCoupon(cpn2);

        Order order1 = new Order("ord01", "cus01", "res01");
        order1.addItem(new OrderItem(m1, 2));
        orderService.addOrder(order1);

        Order order2 = new Order("ord02", "cus01", "res01");
        order2.addItem(new OrderItem(m2, 3));
        orderService.addOrder(order2);

        Order order3 = new Order("ord03", "cus02", "res02");
        order3.addItem(new OrderItem(m3, 1));
        orderService.addOrder(order3);

    }
}

