package com.tunga.repository;

import com.tunga.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Reservation> findByTableIdAndReservationTimeBetween(Long tableId, LocalDateTime start, LocalDateTime end);
    Reservation findByTokenNumber(String tokenNumber);
} 