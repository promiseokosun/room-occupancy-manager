package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.constant.BookingStatuses;
import com.techblazer.lbt.model.Booking;
import com.techblazer.lbt.repo.BookingRepo;
import com.techblazer.lbt.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> getBookingsOrderedByAmountDesc() {
        return bookingRepo.findAllByOrderByAmountDesc();
    }

    @Override
    public List<Booking> getSuccessfulBookings() {

        return bookingRepo.findByStatus(BookingStatuses.success.name());
    }

}
