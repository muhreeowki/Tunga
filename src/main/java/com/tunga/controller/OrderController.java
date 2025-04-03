package com.tunga.controller;

import com.tunga.model.Order;
import com.tunga.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }
    
    @PostMapping
    public String createOrder(@ModelAttribute Order order, Model model) {
        Order savedOrder = orderService.createOrder(order);
        model.addAttribute("order", savedOrder);
        return "order-confirmation";
    }
    
    @GetMapping("/{tokenNumber}")
    public String getOrder(@PathVariable String tokenNumber, Model model) {
        Order order = orderService.getOrderByToken(tokenNumber);
        model.addAttribute("order", order);
        return "order-details";
    }
    
    @GetMapping("/date/{date}")
    public String getOrdersByDate(@PathVariable String date, Model model) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<Order> orders = orderService.getOrdersByDate(dateTime);
        model.addAttribute("orders", orders);
        return "order-list";
    }
} 