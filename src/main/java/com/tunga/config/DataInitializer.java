package com.tunga.config;

import com.tunga.model.*;
import com.tunga.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private DiningRoomRepository diningRoomRepository;
    
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) {
        // Create a restaurant
        final Restaurant restaurant = new Restaurant();
        restaurant.setName("TUNGA Main Branch");
        restaurant.setAddress("123 Main Street");
        restaurant.setCity("New York");
        restaurant.setPhone("+1 234-567-8900");
        restaurant.setEmail("main@tunga.com");
        restaurantRepository.save(restaurant);

        // Create dining rooms
        DiningRoom mainHall = new DiningRoom();
        mainHall.setName("Main Hall");
        mainHall.setDescription("Elegant main dining area");
        mainHall.setRestaurant(restaurant);
        mainHall = diningRoomRepository.save(mainHall);

        DiningRoom privateRoom = new DiningRoom();
        privateRoom.setName("Private Room");
        privateRoom.setDescription("Exclusive private dining area");
        privateRoom.setRestaurant(restaurant);
        privateRoom = diningRoomRepository.save(privateRoom);

        // Create tables
        for (int i = 1; i <= 5; i++) {
            RestaurantTable table = new RestaurantTable();
            table.setTableNumber("M" + i);
            table.setCapacity(4);
            table.setDiningRoom(mainHall);
            restaurantTableRepository.save(table);
        }

        for (int i = 1; i <= 3; i++) {
            RestaurantTable table = new RestaurantTable();
            table.setTableNumber("P" + i);
            table.setCapacity(8);
            table.setDiningRoom(privateRoom);
            restaurantTableRepository.save(table);
        }

        // Create menu items
        MenuItem[] items = {
            createMenuItem("Margherita Pizza", "Classic tomato and mozzarella", 12.99, 2, true),
            createMenuItem("Chicken Tikka", "Grilled chicken in spicy marinade", 15.99, 2, false),
            createMenuItem("Vegetable Biryani", "Fragrant rice with mixed vegetables", 14.99, 2, true),
            createMenuItem("Butter Chicken", "Creamy tomato-based curry", 16.99, 2, false),
            createMenuItem("Paneer Tikka", "Grilled cottage cheese", 13.99, 2, true),
            createMenuItem("Garlic Naan", "Freshly baked garlic bread", 3.99, 1, true),
            createMenuItem("Mango Lassi", "Sweet yogurt drink", 4.99, 1, true)
        };

        Arrays.stream(items).forEach(item -> {
            item.setRestaurant(restaurant);
            menuItemRepository.save(item);
        });
    }

    private MenuItem createMenuItem(String name, String description, double price, int servingSize, boolean isVegetarian) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setServingSize(servingSize);
        item.setVegetarian(isVegetarian);
        return item;
    }
} 