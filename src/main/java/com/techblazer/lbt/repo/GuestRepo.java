package com.techblazer.lbt.repo;

import com.techblazer.lbt.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
}
