package com.tunga.service;

import com.tunga.model.Reservation;
import com.tunga.model.RestaurantTable;
import com.tunga.repository.ReservationRepository;
import com.tunga.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    
    public Reservation createReservation(Reservation reservation) {
        // Validate reservation time (6 hours in advance)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minReservationTime = now.plusHours(6);
        LocalDateTime maxReservationTime = now.plusMonths(1);
        
        if (reservation.getReservationTime().isBefore(minReservationTime) || 
            reservation.getReservationTime().isAfter(maxReservationTime)) {
            throw new IllegalArgumentException("Reservation time must be between 6 hours and 1 month from now");
        }
        
        // Get the selected table
        RestaurantTable table = restaurantTableRepository.findById(reservation.getTable().getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid table selected"));
        
        // Check if table is available
        List<Reservation> existingReservations = reservationRepository.findByTableIdAndReservationTimeBetween(
            table.getId(),
            reservation.getReservationTime().minusHours(2),
            reservation.getReservationTime().plusHours(2)
        );
        
        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("Selected table is not available at the chosen time");
        }
        
        // Set the table
        reservation.setTable(table);
        
        // Generate token number
        reservation.setTokenNumber(UUID.randomUUID().toString().substring(0, 8));
        
        return reservationRepository.save(reservation);
    }
    
    public List<Reservation> getReservationsByDate(LocalDateTime date) {
        LocalDateTime startOfDay = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = date.withHour(23).withMinute(59).withSecond(59);
        return reservationRepository.findByReservationTimeBetween(startOfDay, endOfDay);
    }
    
    public Reservation getReservationByToken(String tokenNumber) {
        return reservationRepository.findByTokenNumber(tokenNumber);
    }
} 