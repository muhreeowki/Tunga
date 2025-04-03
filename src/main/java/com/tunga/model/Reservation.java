package com.tunga.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private int numberOfGuests;
    private LocalDateTime reservationTime;
    private String tokenNumber;
    
    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;
} 