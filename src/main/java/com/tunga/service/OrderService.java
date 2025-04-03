package com.tunga.service;

import com.tunga.model.Order;
import com.tunga.model.OrderItem;
import com.tunga.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order createOrder(Order order) {
        // Set order time
        order.setOrderTime(LocalDateTime.now());
        
        // Calculate estimated delivery time (60-120 minutes)
        int deliveryMinutes = 60 + (int)(Math.random() * 60);
        order.setEstimatedDeliveryTime(order.getOrderTime().plusMinutes(deliveryMinutes));
        
        // Generate token number
        order.setTokenNumber(UUID.randomUUID().toString().substring(0, 8));
        
        // Calculate total amount
        double totalAmount = order.getOrderItems().stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
        order.setTotalAmount(totalAmount);
        
        // Set order reference in order items
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
        }
        
        return orderRepository.save(order);
    }
    
    public List<Order> getOrdersByDate(LocalDateTime date) {
        LocalDateTime startOfDay = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = date.withHour(23).withMinute(59).withSecond(59);
        return orderRepository.findByOrderTimeBetween(startOfDay, endOfDay);
    }
    
    public Order getOrderByToken(String tokenNumber) {
        return orderRepository.findByTokenNumber(tokenNumber);
    }
} 