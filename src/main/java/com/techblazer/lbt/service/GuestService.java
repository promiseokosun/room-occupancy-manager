package com.techblazer.lbt.service;

import com.techblazer.lbt.model.Guest;

import java.util.Optional;

public interface GuestService {
    Guest saveGuest(Guest guest);

    Optional<Guest> getGuest(Long guestId);
}
