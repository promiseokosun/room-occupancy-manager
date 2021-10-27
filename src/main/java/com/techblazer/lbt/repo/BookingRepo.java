package com.techblazer.lbt.repo;

import com.techblazer.lbt.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findAllByOrderByAmountDesc();

    List<Booking> findByStatus(String name);
}
