package com.techblazer.lbt.controller;


import com.techblazer.lbt.dto.CreateBookingRequest;
import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request) {

        Booking booking = new Booking(
                null,
                request.getAmount(),
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );

        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.OK);
    }

}
