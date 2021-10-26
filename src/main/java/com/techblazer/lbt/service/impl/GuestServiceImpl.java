package com.techblazer.lbt.service.impl;

import com.techblazer.lbt.model.Guest;
import com.techblazer.lbt.repo.GuestRepo;
import com.techblazer.lbt.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepo guestRepo;

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepo.save(guest);
    }

    @Override
    public Optional<Guest> getGuest(Long guestId) {
        return guestRepo.findById(guestId);
    }

}
