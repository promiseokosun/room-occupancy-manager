package com.techblazer.lbt.service;

import com.techblazer.lbt.model.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);

    List<Booking> getBookingsOrderedByAmountDesc();

    List<Booking> getSuccessfulBookings();
}
