package com.techblazer.lbt.repo;

import com.techblazer.lbt.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBookingStatusAndRoomType(String name, String type);
}
