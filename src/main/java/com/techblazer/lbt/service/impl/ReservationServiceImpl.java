package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.service.BookingService;
import com.techblazer.lbt.service.GuestService;
import com.techblazer.lbt.service.ReservationService;
import com.techblazer.lbt.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private GuestService guestService;

    public Object createReservation() {

        return null;
    }

}
