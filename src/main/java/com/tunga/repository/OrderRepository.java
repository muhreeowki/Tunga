package com.tunga.repository;

import com.tunga.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
    Order findByTokenNumber(String tokenNumber);
} 