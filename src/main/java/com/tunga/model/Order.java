package com.tunga.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private LocalDateTime orderTime;
    private LocalDateTime estimatedDeliveryTime;
    private String tokenNumber;
    private double totalAmount;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
} 