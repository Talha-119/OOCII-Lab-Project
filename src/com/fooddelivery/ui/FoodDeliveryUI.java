package com.fooddelivery.ui;

import com.fooddelivery.model.Customer;
import com.fooddelivery.model.MenuItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderItem;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.model.User;
import com.fooddelivery.service.AuthService;
import com.fooddelivery.service.CouponService;
import com.fooddelivery.service.OrderService;
import com.fooddelivery.service.RestaurantService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryUI extends JFrame {
    private final AuthService authService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final CouponService couponService;

    private final JComboBox<String> customerBox;
    private final JTextField areaField;
    private final DefaultListModel<String> restaurantListModel;
    private final JList<String> restaurantList;
    private final DefaultListModel<String> menuListModel;
    private final JList<String> menuList;
    private final JSpinner quantitySpinner;
    private final JTextArea outputArea;

    private final List<Customer> customers;
    private List<Restaurant> filteredRestaurants;
    private List<MenuItem> selectedRestaurantMenu;

    public FoodDeliveryUI(AuthService authService, RestaurantService restaurantService, OrderService orderService, CouponService couponService) {
        this.authService = authService;
        this.restaurantService = restaurantService;
        this.orderService = orderService;
        this.couponService = couponService;

        this.customers = loadCustomers();
        this.filteredRestaurants = new ArrayList<>();
        this.selectedRestaurantMenu = new ArrayList<>();

        setTitle("Food Delivery Application");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        customerBox = new JComboBox<>();
        for (Customer customer : customers) {
            customerBox.addItem(customer.getUserID() + " - " + customer.getName());
        }

        areaField = new JTextField(15);
        restaurantListModel = new DefaultListModel<>();
        restaurantList = new JList<>(restaurantListModel);
        menuListModel = new DefaultListModel<>();
        menuList = new JList<>(menuListModel);
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        buildLayout();
        registerActions();
    }

    private void buildLayout() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("Search Restaurants");
        searchButton.setActionCommand("SEARCH");
        topPanel.add(new JLabel("Customer:"));
        topPanel.add(customerBox);
        topPanel.add(new JLabel("Area:"));
        topPanel.add(areaField);
        topPanel.add(searchButton);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(new JScrollPane(restaurantList));
        centerPanel.add(new JScrollPane(menuList));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton showMenuButton = new JButton("Show Menu");
        showMenuButton.setActionCommand("MENU");
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setActionCommand("ORDER");

        actionPanel.add(new JLabel("Quantity:"));
        actionPanel.add(quantitySpinner);
        actionPanel.add(showMenuButton);
        actionPanel.add(placeOrderButton);

        bottomPanel.add(actionPanel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        for (Component component : topPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).addActionListener(e -> searchRestaurants());
            }
        }
        showMenuButton.addActionListener(e -> showMenu());
        placeOrderButton.addActionListener(e -> placeOrder());
    }

    private void registerActions() {
        restaurantList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showMenu();
            }
        });
    }

    private List<Customer> loadCustomers() {
        List<Customer> list = new ArrayList<>();
        for (User user : authService.getUsers()) {
            if (user instanceof Customer) {
                list.add((Customer) user);
            }
        }
        return list;
    }

    private void searchRestaurants() {
        String area = areaField.getText().trim();
        restaurantListModel.clear();
        menuListModel.clear();
        selectedRestaurantMenu.clear();

        if (area.isEmpty()) {
            outputArea.setText("Please enter an area to search restaurants.");
            return;
        }

        filteredRestaurants = restaurantService.findRestaurantWithArea(area);
        if (filteredRestaurants.isEmpty()) {
            outputArea.setText("No restaurant found for area: " + area);
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Restaurants found in ").append(area).append(":\n");
        for (Restaurant restaurant : filteredRestaurants) {
            restaurantListModel.addElement(restaurant.getRestaurantId() + " - " + restaurant.getRestaurantName());
            builder.append(restaurant.getRestaurantName())
                    .append(" | Address: ").append(restaurant.getRestaurantAddress())
                    .append(" | Open: ").append(restaurant.getOpeningTime())
                    .append("-").append(restaurant.getClosingTime())
                    .append("\n");
        }
        outputArea.setText(builder.toString());
    }

    private void showMenu() {
        int selectedIndex = restaurantList.getSelectedIndex();
        menuListModel.clear();
        selectedRestaurantMenu.clear();

        if (selectedIndex < 0 || selectedIndex >= filteredRestaurants.size()) {
            return;
        }

        Restaurant restaurant = filteredRestaurants.get(selectedIndex);
        selectedRestaurantMenu.addAll(restaurant.getMenuItems());

        StringBuilder builder = new StringBuilder();
        builder.append("Menu of ").append(restaurant.getRestaurantName()).append(":\n");
        for (MenuItem item : selectedRestaurantMenu) {
            menuListModel.addElement(item.getFoodId() + " - " + item.getFoodName() + " (Tk " + item.getPrice() + ")");
            builder.append(item.getFoodName())
                    .append(" | Price: Tk ").append(item.getPrice())
                    .append(" | Quantity: ").append(item.getQuantity())
                    .append(" | Available: ").append(item.isAvailable())
                    .append("\n");
        }
        outputArea.setText(builder.toString());
    }

    private void placeOrder() {
        int restaurantIndex = restaurantList.getSelectedIndex();
        int menuIndex = menuList.getSelectedIndex();

        if (restaurantIndex < 0 || menuIndex < 0) {
            outputArea.setText("Please select a restaurant and a menu item first.");
            return;
        }

        Restaurant restaurant = filteredRestaurants.get(restaurantIndex);
        MenuItem menuItem = selectedRestaurantMenu.get(menuIndex);
        int quantity = (int) quantitySpinner.getValue();

        if (!menuItem.isAvailable()) {
            outputArea.setText("Selected menu item is currently unavailable.");
            return;
        }

        if (quantity > menuItem.getQuantity()) {
            outputArea.setText("Requested quantity is not available in stock.");
            return;
        }

        Customer customer = customers.get(customerBox.getSelectedIndex());
        String orderId = String.format("ord%02d", orderService.getAllOrders().size() + 1);
        Order order = new Order(orderId, customer.getUserID(), restaurant.getRestaurantId());
        order.addItem(new OrderItem(menuItem, quantity));

        boolean repeatCustomer = orderService.hasCustomerOrdered(customer.getUserID(), restaurant.getRestaurantId());
        orderService.placeOrder(order);
        menuItem.setQuantity(menuItem.getQuantity() - quantity);
        if (menuItem.getQuantity() == 0) {
            menuItem.setAvailable(false);
        }
        customer.addOrder(orderId);

        StringBuilder builder = new StringBuilder();
        builder.append("Order placed successfully.\n")
                .append("Order ID: ").append(orderId).append("\n")
                .append("Customer: ").append(customer.getName()).append("\n")
                .append("Restaurant: ").append(restaurant.getRestaurantName()).append("\n")
                .append("Item: ").append(menuItem.getFoodName()).append(" x ").append(quantity).append("\n")
                .append("Total: Tk ").append(order.calculateTotal()).append("\n");

        if (repeatCustomer && order.calculateFinalTotal() < order.calculateTotal()) {
            builder.append("Repeat customer coupon applied automatically.\n")
                    .append("Coupon Code: coupon10\n")
                    .append("Final Total: Tk ").append(order.calculateFinalTotal()).append("\n");
        } else {
            builder.append("Final Total: Tk ").append(order.calculateFinalTotal()).append("\n");
        }

        builder.append("Status: ").append(order.getStatus());
        outputArea.setText(builder.toString());
        showMenu();
    }
}
