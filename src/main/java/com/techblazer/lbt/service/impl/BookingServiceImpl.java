package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.repo.BookingRepo;
import com.techblazer.lbt.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

}
