package com.tunga.controller;

import com.tunga.model.Reservation;
import com.tunga.model.RestaurantTable;
import com.tunga.repository.RestaurantTableRepository;
import com.tunga.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    
    @GetMapping
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("tables", restaurantTableRepository.findAll());
        return "reservation-form";
    }
    
    @PostMapping
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        try {
            Reservation savedReservation = reservationService.createReservation(reservation);
            model.addAttribute("reservation", savedReservation);
            return "reservation-confirmation";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("reservation", reservation);
            model.addAttribute("tables", restaurantTableRepository.findAll());
            return "reservation-form";
        }
    }
    
    @GetMapping("/{tokenNumber}")
    public String getReservation(@PathVariable String tokenNumber, Model model) {
        Reservation reservation = reservationService.getReservationByToken(tokenNumber);
        model.addAttribute("reservation", reservation);
        return "reservation-details";
    }
    
    @GetMapping("/date/{date}")
    public String getReservationsByDate(@PathVariable String date, Model model) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<Reservation> reservations = reservationService.getReservationsByDate(dateTime);
        model.addAttribute("reservations", reservations);
        return "reservation-list";
    }
} 