package com.tunga.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private double price;
    private int servingSize;
    private boolean isVegetarian;
    private boolean isSpicy;
    private boolean isGlutenFree;
    private boolean isAvailable;
    
    @Enumerated(EnumType.STRING)
    private MenuCategory category;
    
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public enum MenuCategory {
        APPETIZER,
        MAIN_COURSE,
        DESSERT,
        BEVERAGE,
        SIDE_DISH,
        SALAD,
        SOUP
    }
} 