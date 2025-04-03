package com.tunga.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tableNumber;
    private int capacity;
    
    @ManyToOne
    @JoinColumn(name = "dining_room_id")
    private DiningRoom diningRoom;
    
    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
} 