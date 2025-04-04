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
            // Appetizers
            createMenuItem("Spring Rolls", "Crispy vegetable spring rolls with sweet chili sauce", 6.99, 2, true, false, true, MenuItem.MenuCategory.APPETIZER, "https://example.com/spring-rolls.jpg"),
            createMenuItem("Chicken Wings", "Spicy buffalo wings with blue cheese dip", 8.99, 2, false, true, true, MenuItem.MenuCategory.APPETIZER, "https://example.com/chicken-wings.jpg"),
            createMenuItem("Bruschetta", "Toasted bread with fresh tomatoes, basil, and garlic", 7.99, 2, true, false, true, MenuItem.MenuCategory.APPETIZER, "https://example.com/bruschetta.jpg"),

            // Main Courses
            createMenuItem("Margherita Pizza", "Classic tomato and mozzarella", 12.99, 2, true, false, false, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/pizza.jpg"),
            createMenuItem("Chicken Tikka", "Grilled chicken in spicy marinade", 15.99, 2, false, true, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/chicken-tikka.jpg"),
            createMenuItem("Vegetable Biryani", "Fragrant rice with mixed vegetables", 14.99, 2, true, false, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/biryani.jpg"),
            createMenuItem("Butter Chicken", "Creamy tomato-based curry", 16.99, 2, false, false, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/butter-chicken.jpg"),
            createMenuItem("Paneer Tikka", "Grilled cottage cheese", 13.99, 2, true, false, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/paneer-tikka.jpg"),
            createMenuItem("Grilled Salmon", "Fresh Atlantic salmon with lemon butter sauce", 18.99, 1, false, false, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/salmon.jpg"),
            createMenuItem("Vegetable Curry", "Spicy mixed vegetable curry with basmati rice", 14.99, 1, true, true, true, MenuItem.MenuCategory.MAIN_COURSE, "https://example.com/curry.jpg"),

            // Side Dishes
            createMenuItem("Garlic Naan", "Freshly baked garlic bread", 3.99, 1, true, false, false, MenuItem.MenuCategory.SIDE_DISH, "https://example.com/naan.jpg"),
            createMenuItem("Garlic Mashed Potatoes", "Creamy mashed potatoes with roasted garlic", 5.99, 1, true, false, true, MenuItem.MenuCategory.SIDE_DISH, "https://example.com/mashed-potatoes.jpg"),
            createMenuItem("Grilled Vegetables", "Seasonal vegetables with olive oil and herbs", 6.99, 1, true, false, true, MenuItem.MenuCategory.SIDE_DISH, "https://example.com/grilled-vegetables.jpg"),

            // Salads
            createMenuItem("Caesar Salad", "Romaine lettuce, parmesan, croutons, and Caesar dressing", 9.99, 1, true, false, true, MenuItem.MenuCategory.SALAD, "https://example.com/caesar-salad.jpg"),
            createMenuItem("Greek Salad", "Fresh vegetables, feta cheese, and olives", 10.99, 1, true, false, true, MenuItem.MenuCategory.SALAD, "https://example.com/greek-salad.jpg"),

            // Soups
            createMenuItem("Tomato Basil Soup", "Creamy tomato soup with fresh basil", 7.99, 1, true, false, true, MenuItem.MenuCategory.SOUP, "https://example.com/tomato-soup.jpg"),
            createMenuItem("Chicken Noodle Soup", "Classic chicken noodle soup with vegetables", 8.99, 1, false, false, true, MenuItem.MenuCategory.SOUP, "https://example.com/chicken-soup.jpg"),

            // Desserts
            createMenuItem("Chocolate Lava Cake", "Warm chocolate cake with vanilla ice cream", 8.99, 1, true, false, true, MenuItem.MenuCategory.DESSERT, "https://example.com/lava-cake.jpg"),
            createMenuItem("Tiramisu", "Classic Italian dessert with coffee and mascarpone", 9.99, 1, true, false, true, MenuItem.MenuCategory.DESSERT, "https://example.com/tiramisu.jpg"),
            createMenuItem("Gluten-Free Brownie", "Rich chocolate brownie with ice cream", 7.99, 1, true, false, true, MenuItem.MenuCategory.DESSERT, "https://example.com/brownie.jpg"),

            // Beverages
            createMenuItem("Mango Lassi", "Sweet yogurt drink", 4.99, 1, true, false, true, MenuItem.MenuCategory.BEVERAGE, "https://example.com/mango-lassi.jpg"),
            createMenuItem("Fresh Orange Juice", "Freshly squeezed orange juice", 4.99, 1, true, false, true, MenuItem.MenuCategory.BEVERAGE, "https://example.com/orange-juice.jpg"),
            createMenuItem("Iced Tea", "Freshly brewed iced tea with lemon", 3.99, 1, true, false, true, MenuItem.MenuCategory.BEVERAGE, "https://example.com/iced-tea.jpg"),
            createMenuItem("Sparkling Water", "Premium sparkling water with lime", 2.99, 1, true, false, true, MenuItem.MenuCategory.BEVERAGE, "https://example.com/sparkling-water.jpg")
        };

        Arrays.stream(items).forEach(item -> {
            item.setRestaurant(restaurant);
            menuItemRepository.save(item);
        });
    }

    private MenuItem createMenuItem(String name, String description, double price, int servingSize,
                                  boolean isVegetarian, boolean isSpicy, boolean isGlutenFree,
                                  MenuItem.MenuCategory category, String imageUrl) {
        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setServingSize(servingSize);
        item.setVegetarian(isVegetarian);
        item.setSpicy(isSpicy);
        item.setGlutenFree(isGlutenFree);
        item.setCategory(category);
        item.setImageUrl(imageUrl);
        item.setAvailable(true);
        return item;
    }
} 